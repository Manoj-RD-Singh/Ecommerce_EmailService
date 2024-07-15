package com.ecommerce.ecommerceemailservice.services;

public interface EmailService {
 void sendEmail(String fromEmail, String toEmail, String subject, String body);
}
