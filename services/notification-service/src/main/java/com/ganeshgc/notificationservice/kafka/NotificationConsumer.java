package com.ganeshgc.notificationservice.kafka;

import com.ganeshgc.notificationservice.email.EmailService;
import com.ganeshgc.notificationservice.kafka.order.OrderConfirmation;
import com.ganeshgc.notificationservice.kafka.payment.PaymentConfirmation;

import com.ganeshgc.notificationservice.notification.Notification;
import com.ganeshgc.notificationservice.notification.NotificationRepository;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.ganeshgc.notificationservice.notification.NotificationType.ORDER_CONFIRMATION;
import static com.ganeshgc.notificationservice.notification.NotificationType.PAYMENT_CONFIRMATION;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository repository;
   private final EmailService emailService;
    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info("receive payment confirmation: {}", paymentConfirmation);
        repository.save(
                Notification.builder()
                        .notificationType(PAYMENT_CONFIRMATION)
                        .notificationTime(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
                );
        //send email
        var customerName=paymentConfirmation.customerFirstname()+ " "+paymentConfirmation.customerLastname();
        emailService.sentPaymentSuccessEmail(
                paymentConfirmation.customerEmail(),
                customerName,
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference()
        );

    }
    @KafkaListener(topics = "order-topic")
    public void consumeOrderSuccessNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info("receive order confirmation: {}", orderConfirmation);
        repository.save(
                Notification.builder()
                        .notificationType(ORDER_CONFIRMATION)
                        .notificationTime(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );

        //send email
        var customerName=orderConfirmation.customer().firstname()+ " "+orderConfirmation.customer().lastname();
        emailService.sendOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );




    }


}
