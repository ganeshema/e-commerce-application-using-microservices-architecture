package com.ganeshgc.notificationservice.notification;

import com.ganeshgc.notificationservice.kafka.payment.PaymentConfirmation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}
