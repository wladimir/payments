package com.modusbox.payment;

import com.modusbox.internal.PaymentStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;
import java.util.stream.Stream;

public interface PaymentRepository extends CrudRepository<PaymentEntity, UUID> {
    Stream<PaymentEntity> streamPaymentEntitiesByStatusEquals(final PaymentStatus status);
}
