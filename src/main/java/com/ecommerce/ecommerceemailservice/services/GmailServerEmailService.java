package com.ecommerce.ecommerceemailservice.services;

import com.ecommerce.ecommerceemailservice.utils.EmailUtil;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@Service("GmailServerEmailService")
public class GmailServerEmailService implements  EmailService{

    public void sendEmail(String fromEmail, String toEmail, String subject, String body) {
       final String emailServerLogin = "manojrdsingh@gmail.com"; //requires valid gmail id
        final String emailServerPassword = "axbf rvoi mvav excq"; // correct password for gmail id
      //  toEmail = "manojrdsing@gmail.com"; // can be any email id

        System.out.println("TLSEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailServerLogin, emailServerPassword);
            }
        };
        Session session = Session.getInstance(props, auth);

        EmailUtil.sendEmail(session, fromEmail, toEmail,"Welcome to Ecommerce - Subject", "Welcome to Ecommerce - Body");

    }
}
