package com.eapp.myclubmanager.email;

import com.eapp.myclubmanager.exception.OperationNotPermittedException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;
    @Value("${application.mailing.contact-email}")
    private String contactEmail;

    public EmailService(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    @Async
    public void sendMail(String to, String username, EmailTemplateName emailTemplate, String activationUrl, String activationToken, String subject) throws MessagingException {

        String templateName = emailTemplate.getTemplateName();
        if(templateName == null){
            throw new OperationNotPermittedException("Email template not provided");
        }

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
                mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_MIXED,
                StandardCharsets.UTF_8.name()
        );

        Map<String, Object> messageProperties = new HashMap<>();
        messageProperties.put("username", username);
        messageProperties.put("activationUrl", activationUrl);
        messageProperties.put("activationToken", activationToken);

        Context context = new Context();
        context.setVariables(messageProperties);

        mimeMessageHelper.setFrom(contactEmail);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);

        String messageTemplate = templateEngine.process(templateName, context);

        mimeMessageHelper.setText(messageTemplate, true);

        mailSender.send(mimeMessage);

    }
}
