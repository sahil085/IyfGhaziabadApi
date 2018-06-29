package com.IyfGZB.NotificationServices;


import com.IyfGZB.domain.Seminar;
import com.IyfGZB.services.UserInfoOperation;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Service
public class SeminarNotificationService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private VelocityEngine velocityEngine;

   @Value("${seminar.subject}")
   private String subject;

   @Autowired
   private UserInfoOperation userInfoOperation;

    @Async
    public void sendEmail(Seminar seminar) throws MessagingException {

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("title", seminar.getTitle());
        model.put("speakername",seminar.getSpeakerName());
        String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "/template/seminarEmailTemplte", "UTF-8", model);

        System.out.println(text);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
//        mimeMessageHelper.setFrom();
        mimeMessageHelper.setTo(String.join(",",userInfoOperation.getallEmails()));
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(text, true);

        javaMailSender.send(mimeMessage);


    }
}
