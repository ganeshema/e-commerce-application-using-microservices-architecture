package com.ganeshgc.notificationservice.email;

import com.ganeshgc.notificationservice.kafka.order.Product;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ganeshgc.notificationservice.email.EmailTemplates.ORDER_CONFIRMATION;
import static com.ganeshgc.notificationservice.email.EmailTemplates.PAYMENT_CONFIRMATION;
import static java.nio.charset.StandardCharsets.UTF_8;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender; // Handles email sending
    private final SpringTemplateEngine templateEngine; // Thymeleaf template engine for generating email HTML

    /**
     * Sends a payment success email to the customer.
     * @param destinationEmail recipient's email address
     * @param customerName recipient's name
     * @param amount payment amount
     * @param orderReference reference ID for the order
     * @throws MessagingException if email sending fails
     */
    @Async // Runs this method asynchronously on a separate thread
    public void sentPaymentSuccessEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference
    ) throws MessagingException {
        // Create a new MimeMessage (used for sending complex emails)
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        // Configure the MimeMessage for multipart mode and UTF-8 encoding
        MimeMessageHelper mimeMessageHelper =
                new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_RELATED, UTF_8.name());

        // Set the sender email address
        mimeMessageHelper.setFrom("ganeshgc@gmail.com");

        // Get the email template name for payment confirmation
        final String templateName = PAYMENT_CONFIRMATION.getTemplate();

        // Prepare variables for the email template
        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("amount", amount);
        variables.put("orderReference", orderReference);

        // Set variables in Thymeleaf context
        Context context = new Context();
        context.setVariables(variables);

        // Set the email subject
        mimeMessageHelper.setSubject(PAYMENT_CONFIRMATION.getSubject());

        try {
            // Process the Thymeleaf template with the context to generate HTML
            String htmlTemplate = templateEngine.process(templateName, context);

            // Set the generated HTML as the email body (true for HTML content)
            mimeMessageHelper.setText(htmlTemplate, true);

            // Set the recipient email address
            mimeMessageHelper.setTo(destinationEmail);

            // Send the email
            mailSender.send(mimeMessage);

            // Log success
            log.info(String.format("INFO -- Email sent to %s successfully with template: %s", destinationEmail, htmlTemplate));
        } catch (MessagingException e) {
            // Log a warning if email sending fails
            log.warn("Warning: Cannot send email to " + destinationEmail, e);
        }
    }

    /**
     * Sends an order confirmation email with product details.
     * @param destinationEmail recipient's email address
     * @param customerName recipient's name
     * @param amount total order amount
     * @param orderReference reference ID for the order
     * @param products list of products in the order
     * @throws MessagingException if email sending fails
     */
    @Async // Runs this method asynchronously on a separate thread
    public void sendOrderConfirmationEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference,
            List<Product> products
    ) throws MessagingException {
        // Create a new MimeMessage
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        // Configure the MimeMessage for multipart mode and UTF-8 encoding
        MimeMessageHelper mimeMessageHelper =
                new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_RELATED, UTF_8.name());

        // Set the sender email address
        mimeMessageHelper.setFrom("ganeshgc@gmail.com");

        // Get the email template name for order confirmation
        final String templateName = ORDER_CONFIRMATION.getTemplate();

        // Prepare variables for the email template
        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("totalAmount", amount);
        variables.put("orderReference", orderReference);
        variables.put("products", products); // Includes product details in the email

        // Set variables in Thymeleaf context
        Context context = new Context();
        context.setVariables(variables);

        // Set the email subject
        mimeMessageHelper.setSubject(ORDER_CONFIRMATION.getSubject());

        try {
            // Process the Thymeleaf template with the context to generate HTML
            String htmlTemplate = templateEngine.process(templateName, context);

            // Set the generated HTML as the email body (true for HTML content)
            mimeMessageHelper.setText(htmlTemplate, true);

            // Set the recipient email address
            mimeMessageHelper.setTo(destinationEmail);

            // Send the email
            mailSender.send(mimeMessage);

            // Log success
            log.info(String.format("INFO -- Email sent to %s successfully with template: %s", destinationEmail, htmlTemplate));
        } catch (MessagingException e) {
            // Log a warning if email sending fails
            log.warn("Warning: Cannot send email to " + destinationEmail, e);
        }
    }
}
