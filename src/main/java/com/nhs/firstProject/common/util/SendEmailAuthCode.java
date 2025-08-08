package com.nhs.firstProject.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.nhs.firstProject.common.exception.ProjectException;

public class SendEmailAuthCode {

    public static Map<String,String> doSendEmailAuthCode(String userEmail, HttpServletRequest request) throws ProjectException {
    	Map<String, String> returnData = new HashMap<String, String>();
        String authCode = generateAuthCode();
        long createTime = System.currentTimeMillis();

        HttpSession session = request.getSession();
        session.setAttribute("authCode", authCode);
        session.setAttribute("authEmail", userEmail);
        session.setAttribute("authCodeCreatedTime", createTime);

        try {
            MailUtil.sendEmail(userEmail, authCode); // 이메일 전송
            returnData.put("result", "AUTH_CODE_SENT");
        } catch (Exception e) {
            e.printStackTrace();
            returnData.put("result", "EMAIL_SEND_ERROR");
        }
        
        return returnData ;
    }

    public static String generateAuthCode() {
        return String.valueOf(new Random().nextInt(900000) + 100000); // 6자리 인증번호 생성
    }
}
