package com.nhs.firstProject.main.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;

import net.sf.json.JSONObject;

@Service("homeService")
public class HomeServiceImp implements HomeService{
	private final static String NAME_SPACE = "home.";
	private final static Logger LOG = LoggerFactory.getLogger(HomeServiceImp.class);
	
	@Resource(name="sqlSession")
	private SqlSessionTemplate sqlSession;
	
	@Override
	public Map<String, Object> getUserInfo(String strUserId) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("PROCEDURE :: {}, PARAMETER :: {}", NAME_SPACE+"SELECT_USER_INFO", strUserId);
		}
		HashMap<String, Object> hmParam = new HashMap<String, Object>();
		hmParam.put("USER_ID", strUserId);
		return sqlSession.selectOne(NAME_SPACE + "SELECT_USER_INFO", hmParam);
	}
	
	@Override
	public Map<String, Object> getLoginInfo(String strUserId) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("PROCEDURE :: {}, PARAMETER :: {}", NAME_SPACE+"SELECT_LOGIN_INFO", strUserId);
		}
		HashMap<String, Object> hmParam = new HashMap<String, Object>();
		hmParam.put("USER_ID", strUserId);
		
		return sqlSession.selectOne(NAME_SPACE + "SELECT_LOGIN_INFO", hmParam);
	}
	
	@Override
	public int updateUserLoginStatus(Map<String, Object> mapUserInfo) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("PROCEDURE :: {}, PARAMETER :: {}", NAME_SPACE+"UPDATE_USER_LOGIN_STATUS", mapUserInfo);
		}
		return sqlSession.update(NAME_SPACE +"UPDATE_USER_LOGIN_STATUS", mapUserInfo);
	}
	
	@Override
	public Map<String, Object> compareRequestRole(Map<String, Object> paramData) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("PROCEDURE :: {}, PARAMETER :: {}", NAME_SPACE+"SELECT_COMPARE_REQUEST_ROLE", paramData);
		}
		Map<String, Object> resultData = sqlSession.selectOne(NAME_SPACE + "SELECT_COMPARE_REQUEST_ROLE", paramData);
		paramData.put("resultData", resultData);
		return paramData;
	}
	
	@Override
	public Map<String, Object> selectMenu(Map<String, Object> paramData) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("PROCEDURE :: {}, PARAMETER :: {}", NAME_SPACE+"SELECT_MENU", paramData);
		}
		Map<String, Object> resultData = sqlSession.selectOne(NAME_SPACE + "SELECT_MENU", paramData);
		paramData.put("resultData", resultData);
		return paramData;
	}
}
