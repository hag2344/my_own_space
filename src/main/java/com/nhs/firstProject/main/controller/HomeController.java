package com.nhs.firstProject.main.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nhs.firstProject.common.vo.UserInfoVo;
import com.nhs.firstProject.main.service.HomeService;
import com.nhs.firstProject.common.exception.ProjectException;
import com.nhs.firstProject.common.ComConstant;
import com.nhs.firstProject.common.vo.LoginResult;


@Controller
public class HomeController {

	private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

	@Inject
	BCryptPasswordEncoder passwordEncoder;
	
	@Resource(name = "homeService")
	private HomeService homeService;
	
	/**
	 * @Method 설명 : 프로젝트 접속
	 */
	@RequestMapping(value = "/home.do", method = RequestMethod.GET)
	public String home(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		HashMap<String, Object> sessionMap = (HashMap<String, Object>) session.getAttribute("sessionMap");
		LOG.info("==============Home START====================");
		UserInfoVo userMap = sessionMap == null ? null : (UserInfoVo) sessionMap.get("userMap");
	

		if (userMap == null) {
			return "redirect:login.do";
		} else {
			String strView = null;
			try {
				session = request.getSession();
				session.setAttribute("sessionMap", sessionMap);
				strView = "redirect:/menu.do";
			} catch (Exception ie) {
				LOG.error("Session Exception :: {} ", ie);
			}
			return strView;
		}

	}
	
    /**
     * 
     * @Method 설명 : 로그인 화면 요청
     * 
     */
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String doLogin(HttpServletResponse request) {
		LOG.info("===================Login START===================");

		return "firstproject/login/login";
	}
	
	/**
	 * @Method 설명 : 로그인확인 요청 
	 */
	@RequestMapping(value = "/loginCheck.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView loginCheck(ModelAndView mav, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, @RequestParam Map<String, Object> paramData) throws ProjectException, IOException {
		response.setContentType("application/x-json; charset=UTF-8");
		LOG.info("===== LoginCheck START =====");
		JSONObject joReturn = new JSONObject();
		Writer out = null;

		JSONObject jo = new JSONObject();

		try {
			String strUserId = paramData.get("strUserId").toString();
			String strBimilbun = paramData.get("strBimilbun").toString();

			Map<String, Object> userInfo = homeService.getUserInfo(strUserId);
			
			if(userInfo != null && userInfo.size() > 0) {
				
				if(userInfo != null && userInfo.size() > 0) {
					joReturn = runLoginProcess(strUserId, strBimilbun, session, userInfo, request);
				}

			}else {
				jo.put("loginResult", ComConstant.LOGIN_FAIL);
				jo.put("failReason", "입력하신 ID, 패스워드를 확인하세요.");
				jo.put("msgTitle", "로그인에 실패하였습니다.");
				joReturn.put("returnData", jo);
			}
			out = response.getWriter();
			out.write(joReturn.toString());

		} catch (Exception ex) {
			LOG.error("LoginCheck Error :: {}", ex.getMessage());
			throw new ProjectException(ex);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
		return null;
	}
	
	/**
	 * @Method 설명 : 로그인프로세스 유저체크분석 
	 */
	private JSONObject runLoginProcess(String strUserId, String strBimilbun, HttpSession session, Map<String, Object> userInfo,  HttpServletRequest request) throws ProjectException {
		JSONObject joReturn = new JSONObject();
		LoginResult loginResult = new LoginResult();
		String clientIp = request.getRemoteAddr();
		
		boolean stepOK = true;
		
		try {
			Map<String, Object> returnData = new HashMap<String, Object>();
			Map<String, Object> userInfoMap = homeService.getLoginInfo(strUserId);
			
			if(userInfoMap.isEmpty()) {
				 loginResult.setResult(ComConstant.NOT_OK);
				 loginResult.setFailReason("입력하신 ID, 패스워드를 확인하세요.");
				 loginResult.setMsgTitle("로그인에 실패하였습니다.");
				 stepOK = false;
			}
					
			if(stepOK){
				//1. 패스워드체크 
				String encodeBimilbun = userInfoMap.get("USER_PASSWORD").toString();
				boolean bimilBunCheck = passwordEncoder.matches(strBimilbun, encodeBimilbun );
			
				if (bimilBunCheck == false){

					 loginResult.setResult(ComConstant.NOT_OK);
					 loginResult.setFailReason("입력하신 ID, 패스워드를 확인하세요.");
					 loginResult.setMsgTitle("로그인에 실패하였습니다.");
					 returnData.put("USER_ID", strUserId);
					 stepOK = false;
				}
			}
					
			if(stepOK){
				//2. 상태채크
				String userStatus = userInfoMap.get("STATUS_CD").toString();
				
				if(!userStatus.equals("T")) {
					 loginResult.setResult(ComConstant.NOT_OK);
					 loginResult.setFailReason("관리자에게 문의하세요.");
					 loginResult.setMsgTitle("계정이 중지상태입니다.");
					 stepOK = false;
				}
			}
							
			if(stepOK){
				LOG.info("사용자 접속 IP==>{}",clientIp);
				returnData.put("USER_ID", strUserId);
				int intLoginStatusUpdate = homeService.updateUserLoginStatus(returnData);
				LOG.info("사용자 사이트 이용 상태 변경 저장 결과==>{}", intLoginStatusUpdate);
				
				LOG.info("Login Success :: {}", strUserId);
				HashMap<String, Object> sessionMap = new HashMap<String, Object>();
				UserInfoVo userMap = getUserInfo(strUserId, request);		//세션정보
				sessionMap.put("userMap", userMap);

				session.setAttribute("sessionMap", sessionMap);
				LOG.info("################ session :: " + session.getAttribute("sessionMap").toString());
				
				returnData.put("loginResult", ComConstant.LOGIN_SUCCESS);
				returnData.put("result", loginResult.getResult());
				returnData.put("USER_ID", strUserId);
				
			}else{
				
				 returnData.put("loginResult", ComConstant.LOGIN_FAIL);
				 returnData.put("result", loginResult.getResult());
				 returnData.put("USER_ID", strUserId);		
				 
			 	 returnData.put("failReason", loginResult.getFailReason());
			 	 returnData.put("msgTitle", loginResult.getMsgTitle());	 	
			}
			
			joReturn.put("returnData", returnData);
		} catch (Exception ex) {
			LOG.error("RunLoginProcess Error", ex);
			throw new ProjectException(ex);
		}
		return joReturn;	
	}

	/**
	 * @Method 설명 : 세션에 유저정보를 담기위한 Method 
	 */
	private UserInfoVo getUserInfo(String strUserId, HttpServletRequest request) throws ProjectException {
		UserInfoVo userInfoVo = new UserInfoVo(strUserId);
		request.getHeader("X-Forwarded-For");
		userInfoVo.setStrClientIP(request.getRemoteAddr());
		
		Map<String, Object> userInfoMap = homeService.getUserInfo(strUserId);
		
		if(userInfoMap != null) {
			userInfoVo.setStrRoleName(userInfoMap.get("ROLE_NAME") != null ? userInfoMap.get("ROLE_NAME").toString() : "");
			userInfoVo.setStrName(userInfoMap.get("USER_NAME") != null ? userInfoMap.get("USER_NAME").toString() : "");
			userInfoVo.setIntRoleId(userInfoMap.get("ROLE_ID") != null ? Integer.parseInt(userInfoMap.get("ROLE_ID").toString()) : ComConstant.ROLE_NOT_EXIST);
			userInfoVo.setStrStatus(userInfoMap.get("STATUS_CD") != null ? userInfoMap.get("STATUS_CD").toString() : "");
			userInfoVo.setStrMobileNumber(userInfoMap.get("PHONE_NUMBER") != null ? userInfoMap.get("PHONE_NUMBER").toString() : "");
			userInfoVo.setStrEmail(userInfoMap.get("USER_EMAIL") != null ? userInfoMap.get("USER_EMAIL").toString() : "");
			userInfoVo.setStrPrivateKey(Long.toString((new Date()).getTime()));
		
		} 

		return userInfoVo;
	}
	
	/**
	 * @Method 설명 : 로그아웃 요청 
	 */
	@RequestMapping(value = "/logout.do", method=RequestMethod.GET)
	public String doLogout(HttpServletRequest request, HttpSession session, @RequestParam Map<String, Object> paramData) throws Exception {
		HashMap<String, Object> sessionMap = (HashMap<String, Object>) session.getAttribute("sessionMap");
		UserInfoVo userMap = sessionMap == null ? null : (UserInfoVo)sessionMap.get("userMap");
		String strLoginType = paramData.get("strLoginType") !=null ? paramData.get("strLoginType").toString() : "PC";
		JSONObject joParam = new JSONObject();
		JSONObject jo = new JSONObject();
		jo.put("USER_ID", userMap.getStrUserId());
		jo.put("LOGIN_RESULT", "D");
		jo.put("resultSms", "Logout처리");
		joParam.put("returnData", jo);
		//int intResult = homeService.insertLoginResult(joParam, userMap.getStrClientIP(), strLoginType);
		request.getSession();
		session.invalidate();
		LOG.info("===============Session Invalidate ============");
		return "redirect:/login.do";	
	}
	
	/**
	 * @Method 설명 : 로그인 시도 후 메인 화면요청 
	 */
	@RequestMapping(value = "/menu.do", method = RequestMethod.GET)
	public String moveMenu(HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		HashMap<String, Object> sessionMap = (HashMap<String, Object>) session.getAttribute("sessionMap");
		LOG.info("==============Menu START====================");

		UserInfoVo userMap = sessionMap == null ? null : (UserInfoVo) sessionMap.get("userMap");

		if (userMap == null) {
			return "redirect:/login.do";
		} else {
			String strView = null;
			try {
				session = request.getSession();
				session.setAttribute("sessionMap", sessionMap);
				strView = "firstproject/main/mainBoard";
			} catch (IllegalStateException ie) {
				LOG.error("Session Exception :: {}", ie);
			}
			return strView;
		}
	}
	
	/**
     * 
     * @Method 설명 : 로그인 화면 요청
     * 
     */
	@RequestMapping(value = "/idFind.do", method = RequestMethod.GET)
	public String doIdFind(HttpServletResponse request) {
		LOG.info("===================ID Find START===================");

		return "firstproject/login/idFind";
	}
	
	/**
     * 
     * @Method 설명 : 로그인 화면 요청
     * 
     */
	@RequestMapping(value = "/bimilbunFind.do", method = RequestMethod.GET)
	public String doBimilbunFind(HttpServletResponse request) {
		LOG.info("===================Bimilbun Find START===================");

		return "firstproject/login/bimilbunFind";
	}
	
	/**
     * 
     * @Method 설명 : 로그인 화면 요청
     * 
     */
	@RequestMapping(value = "/signUp.do", method = RequestMethod.GET)
	public String doSignUp(HttpServletResponse request) {
		LOG.info("===================Sign Up START===================");

		return "firstproject/login/signUp";
	}

}
