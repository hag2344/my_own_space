package com.nhs.firstProject.common.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.nhs.firstProject.common.exception.ProjectException;

public class VerifyEmailAuthCode {

	public static Map<String,String> doVerifyEmailAuthCode(String userEmail, String inputCode, HttpServletRequest request) throws ProjectException {
		Map<String, String> returnData = new HashMap<String, String>();

        HttpSession session = request.getSession();
        String savedCode = (String) session.getAttribute("authCode");           // 세션에 저장된 인증번호
        String savedEmail = (String) session.getAttribute("authEmail");         // 세션에 저장된 이메일
        Long createdTime = (Long) session.getAttribute("authCodeCreatedTime");  // 세션에 저장된 인증번호 생성 시간
        boolean stepOK = true;

        if (savedCode == null || savedEmail == null || createdTime == null) {
        	returnData.put("result", "NO_AUTH_CODE");
        	stepOK = false;
        }

        if (!savedEmail.equals(userEmail) && stepOK) {
        	returnData.put("result", "EMAIL_MISMATCH");
        	stepOK = false;
        }

        long now = System.currentTimeMillis();
        if (now - createdTime > 3 * 60 * 1000 && stepOK) { // 인증번호 유효 기간 3분
            session.removeAttribute("authCode");
            session.removeAttribute("authEmail");
            session.removeAttribute("authCodeCreatedTime");
            returnData.put("result", "AUTH_CODE_EXPIRED");
            stepOK = false;
        }

        if (!savedCode.equals(inputCode) && stepOK) {
        	returnData.put("result", "AUTH_CODE_INCORRECT");
        	stepOK = false;
        }
        
        if(stepOK) {
        	// 인증 성공 처리
            session.setAttribute("emailVerified", true);
            returnData.put("result", "AUTH_SUCCESS"); 
        }
         
        return returnData;
    }
}
