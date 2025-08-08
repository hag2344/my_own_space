package com.nhs.firstProject.common.interceptor;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import com.nhs.firstProject.common.vo.UserInfoVo;
import com.nhs.firstProject.main.service.HomeService;

public class CommonInterceptor implements HandlerInterceptor{

	private static final Logger LOG = LoggerFactory.getLogger(CommonInterceptor.class);
	@Resource(name="homeService")
	private HomeService homeService;
	
	@SuppressWarnings("unchecked")
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws UnsupportedEncodingException {
		
		String requestURI = StringUtils.removeStart(request.getServletPath(), "/");
		
		HttpSession session = request.getSession();
		/* session.setMaxInactiveInterval(60); */

		//로그인 환경 분석
		HashMap<String, Object> sessionMap = (HashMap<String, Object>) session.getAttribute("sessionMap");
		UserInfoVo userMap = sessionMap == null ? null : (UserInfoVo)sessionMap.get("userMap");
		LOG.info("SESSION ID==>{}", session.getId());
		//예외처리
		if(!StringUtils.contains(requestURI, "login.do") && !StringUtils.contains(requestURI, "home.do")
			&& !StringUtils.contains(requestURI, "loginCheck.do") && !StringUtils.contains(requestURI, "logout.do")
			&& !StringUtils.contains(requestURI, "loginSecurityCheck.do") && !StringUtils.contains(requestURI, "idFind.do")
			&& !StringUtils.contains(requestURI, "bimilbunFind.do") && !StringUtils.contains(requestURI, "signUp.do")
			&& !StringUtils.contains(requestURI, "restapi")) {
			if(userMap == null) {
				String[] ar = new String[3];
				ar[0] = request.getRemoteAddr();
				ar[1] = requestURI;
				ar[2] = session != null ? session.toString() : "session null";
				
				LOG.info("Interceptor::UserInfo in Session does not exist. \n\t client IP : {}, Request URL : {}, \n\t session Map : {}", ar);
				
				//로그인 화면으로 이동 
				try {
					if(isAjaxRequest(request)) {
						response.sendError(999);
					} else {
						//response.sendError(999);
						response.sendRedirect(request.getContextPath()+ "/login.do");
					}
				} catch (RuntimeException e) {
					LOG.error("Session Exception :: {}", e);
				} catch (Exception e) {
					LOG.error("Session Exception :: {}", e);
				}
				return false;
			} else {
				if(isAjaxRequest(request)) {
					response.setContentType("application/x-json; charset=UTF-8");
				}
				
				String[] ar = new String[3];
				ar[0] = request.getRemoteAddr();
				ar[1] = requestURI;
				ar[2] = userMap != null ? userMap.toString() : "session null";
			
				if (checkRole(userMap, requestURI) == false) {
					try {
						LOG.info("비정상적 접근 ========== \n\t client IP : {}, Request URL : {}, \n\t user info : {}", ar);
						response.sendRedirect(request.getContextPath()+"/error.jsp");
						//response.sendError(999);
					} catch (IOException e) {
						LOG.error("권한 Exception :: {}", e);
					}
					return false;					
				}
			}
		}
		return true;
	}
	
	public boolean checkRole(UserInfoVo userMap, String requestURI) {
		HashMap<String, Object> roleMap = new HashMap<String, Object>();
		requestURI = requestURI.replaceAll("main/", "");
		roleMap.put("USER_ID", userMap.getStrUserId());
		roleMap.put("URL_PATH", requestURI);

		boolean rtnCheck = true;
		Map<String, Object> iReturn = homeService.selectMenu(roleMap);
		if(iReturn.get("resultData") != null ) {
			Map<String, Object> hmMap = homeService.compareRequestRole(roleMap);
			if (hmMap.get("resultData") == null ) {
				rtnCheck = false;
			}
		}
		return rtnCheck;
	}
	
	private boolean isAjaxRequest(HttpServletRequest request) {
        String header = request.getHeader("AJAX");
        if (header != null && "true".equals(header)){
        	return true;
        } else {
        	return false;
        }
    }
}
