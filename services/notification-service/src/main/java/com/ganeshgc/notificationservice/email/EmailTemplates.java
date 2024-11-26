package com.ganeshgc.notificationservice.email;

import lombok.Getter;

@Getter
public enum EmailTemplates {

    // Enum constants representing different email templates with associated metadata
    PAYMENT_CONFIRMATION("payment-confirmation.html", "payment successfully processed"),
    ORDER_CONFIRMATION("order-confirmation.html", "order confirmation");

    // Private fields to hold the template file name and subject of the email
    private final String template; // Name of the template file (e.g., HTML file)
    private final String subject;  // Subject of the email for this template

    /**
     * Constructor for the enum. Enums in Java can have constructors, but they must be private or package-private.
     * This constructor initializes the fields `template` and `subject` for each enum constant.
     * @param template the name of the template file
     * @param subject the email subject
     */
    EmailTemplates(String template, String subject) {
        this.template = template; // Assigns the provided template name to the `template` field
        this.subject = subject;   // Assigns the provided subject to the `subject` field
    }
}
