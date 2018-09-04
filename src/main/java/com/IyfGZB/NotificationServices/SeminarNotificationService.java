package com.IyfGZB.NotificationServices;


import com.IyfGZB.domain.Seminar;
import com.IyfGZB.services.UserInfoOperation;
import com.IyfGZB.util.DateUtil;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SeminarNotificationService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private VelocityEngine velocityEngine;

   @Value("${seminar.subject}")
   private String subject;

   @Value("${url}")
   private String actionLink;

   @Value("${seminar.updation.subject}")
   private String seminarUpdateSubject;

   @Autowired
   private UserInfoOperation userInfoOperation;

    @Async
    public void sendEmail(Seminar seminar) throws MessagingException {

//        if(seminar.isPoster()){
//
//        }else{
//
//        }

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("title", seminar.getTitle());
        model.put("speakerName",seminar.getSpeakerName());
        model.put("startTime",seminar.getStartTime());
        model.put("venue",seminar.getVenue());
        model.put("endTime",seminar.getEndTime());
        model.put("date",DateUtil.toDate(seminar.getDate()));
        model.put("day",DateUtil.getDateInString(seminar.getDate()));
        model.put("speakerDesc",seminar.getSpeakerDescription());
        model.put("seminarDesc",seminar.getSeminarDescription());
        String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "/template/seminarEmailTemplte", "UTF-8", model);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
       List<String> list= userInfoOperation.getallEmails(seminar.getCategory(),seminar.getCity());

        mimeMessageHelper.setFrom(new InternetAddress("vermasahil269@gmail.com"));
        mimeMessageHelper.setTo(list.toArray(new String[list.size()]));
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(text, true);

        javaMailSender.send(mimeMessage);


    }

    @Async
    public void sendUpdationEmail(Seminar seminar) throws MessagingException {

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("title", seminar.getTitle());
        model.put("speakerName",seminar.getSpeakerName());
        model.put("startTime",seminar.getStartTime());
        model.put("venue",seminar.getVenue());
        model.put("endTime",seminar.getEndTime());
        model.put("date",DateUtil.toDate(seminar.getDate()));
        model.put("day",DateUtil.getDateInString(seminar.getDate()));
        model.put("speakerDesc",seminar.getSpeakerDescription());
        model.put("seminarDesc",seminar.getSeminarDescription());
        String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "/template/seminarEmailTemplte", "UTF-8", model);

        System.out.println(text);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        List<String> list= userInfoOperation.getallEmails(seminar.getCategory(), seminar.getCity());

//        mimeMessageHelper.setFrom(new InternetAddress("vermasahil269@gmail.com"));
        mimeMessageHelper.setTo(list.toArray(new String[list.size()]));
        mimeMessageHelper.setSubject(seminarUpdateSubject);
        mimeMessageHelper.setText(text, true);

        javaMailSender.send(mimeMessage);


    }

}
