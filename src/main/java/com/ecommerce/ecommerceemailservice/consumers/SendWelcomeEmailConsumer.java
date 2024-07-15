package com.ecommerce.ecommerceemailservice.consumers;

import com.ecommerce.ecommerceemailservice.dtos.EmailDto;
import com.ecommerce.ecommerceemailservice.services.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SendWelcomeEmailConsumer {

    @Autowired
    @Qualifier("GmailServerEmailService")
    EmailService emailService;

    @Autowired
    ObjectMapper objectMapper;

    @KafkaListener(topics = "sendEmail",groupId = "emailService")
    public void sendWelcomeEmail(String message){
        try{
            //emailService
            EmailDto emailDto = objectMapper.readValue(message, EmailDto.class);
            emailService.sendEmail(emailDto.getFrom(), emailDto.getTo(), emailDto.getSubject(), emailDto.getBody());
        }catch(Exception ex){
            log.error(""+ex);
        }

    }
}
