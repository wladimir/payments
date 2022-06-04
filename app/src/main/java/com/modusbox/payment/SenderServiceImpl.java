package com.modusbox.payment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.modusbox.KafkaMessageProducer;
import com.modusbox.internal.PaymentStatus;
import com.modusbox.json.PaymentJson;
import com.modusbox.json.PaymentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SenderServiceImpl implements SenderService {
    @Qualifier("senderTaskExecutor")
    private final TaskExecutor taskExecutor;

    private final PaymentRepository repository;

    private final KafkaMessageProducer messageProducer;

    private final ObjectMapper objectMapper;

    @Scheduled(fixedRate = 1_000, initialDelay = 2_000)
    @Transactional
    public void send() {
        repository.streamPaymentEntitiesByStatusEquals(PaymentStatus.created)
                .limit(100)
                .parallel()
                .forEach(entity -> {
                    if (queueFull()) return;

                    taskExecutor.execute(() -> {
                        try {
                            final PaymentJson paymentJson = PaymentMapper.INSTANCE.toJson(entity.toPayment());
                            final String payload = objectMapper.writeValueAsString(paymentJson);

                            messageProducer.sendMessage(payload);
                        } catch (JsonProcessingException e) {
                            log.error("failed to serialize payment");
                        }

                        updateStatus(entity);
                    });
                });
    }

    public void updateStatus(final PaymentEntity entity) {
        try {
            entity.setStatus(PaymentStatus.sent);
            repository.save(entity);
        } catch (ObjectOptimisticLockingFailureException e) {
            log.warn("failed to save entity with id " + entity.getId());
        }
    }

    private boolean queueFull() {
        if (taskExecutor instanceof ThreadPoolTaskExecutor) {
            if (((ThreadPoolTaskExecutor) taskExecutor).getThreadPoolExecutor().getQueue().remainingCapacity() <= 1) {
                log.warn("No capacity to send!");
                return true;
            }
        }
        return false;
    }
}
