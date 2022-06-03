package com.modusbox.payment;

import com.modusbox.internal.Payment;
import com.modusbox.internal.PaymentStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class SenderServiceImpl implements SenderService {
    @Qualifier("senderTaskExecutor")
    private final TaskExecutor taskExecutor;

    private final PaymentRepository repository;

    @Scheduled(fixedRate = 1_000, initialDelay = 5_000)
    public void send() {
        final List<PaymentEntity> entities = repository.streamPaymentEntitiesByStatusEquals(PaymentStatus.created)
                .limit(100)
                .toList();

        for (PaymentEntity entity : entities) {
            if (queueFull()) return;

            taskExecutor.execute(() -> {
                Payment payment = entity.toPayment();
                System.out.println("Sending to Kafka: " + payment.id());

                updateStatus(entity);
            });
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

    @Transactional
    public void updateStatus(final PaymentEntity entity) {
        try {
            entity.setStatus(PaymentStatus.sent);
            repository.save(entity);
        } catch (ObjectOptimisticLockingFailureException e) {
            log.warn("Failed to save entity with id " + entity.getId());
        }
    }
}
