package com.nhs.firstProject.main.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

public interface HomeService {

	Map<String, Object> getUserInfo(String strUserId); // 사용자 정보 
	
	Map<String, Object> getLoginInfo(String strUserId); // 로그인 정보 
	
	int updateUserLoginStatus(Map<String, Object> mapUserInfo) throws Exception; // 사용자 로그인 상태 업데이트 
	
	Map<String, Object> compareRequestRole(Map<String, Object> paramData); // 사용자 권한 확인
	
	Map<String, Object> selectMenu(Map<String, Object> paramData); // 메뉴 조회
}
