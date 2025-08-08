package com.nhs.firstProject.common.util;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

public class MailUtil {

    public static void sendEmail(String toEmail, String authCode) throws MessagingException {
        final String username = "your_naver_id@naver.com";      // 네이버 이메일 주소
        final String password = "your_app_password";            // 앱 비밀번호 사용

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.naver.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject("이메일 인증 코드");
        message.setText("인증 코드는 다음과 같습니다: " + authCode);

        Transport.send(message);
    }
}

