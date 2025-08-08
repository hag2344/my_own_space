package com.nhs.firstProject.common.vo;

import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nhs.firstProject.common.exception.ProjectException;
import com.nhs.firstProject.common.util.AesCrypto;

public class UserInfoVo implements Serializable{
	private static final Logger LOG = LoggerFactory.getLogger("com.nhs.firstProject");
	
	private static final long serialVersionUID = 1L;
	
	private int intLoginResult = 0; // 0 : logout   1 : login   2: failed
	private String strUserId;
	private String strPassword;
	private String strLoginMessage;
	private String strName;
	private String strPhoneNumber;
	private String strOrgId;
	private String strOrgName;
	private String strMobileNumber;
	private String strEmail;	
	private String strPath;
	private String strPathName;
	private int intUserType;
	private String strIsSMS;
	private String strPathLevel;
	private int intRoleId;
	private String strCheckIntro;
	private String strCheckIntroSode;
	private String strStatus;
	private String strParentPath;
	private String strParentPosx;		
	private String strParentPosy;
	private String strInitMenuUrl;		// 최초에 보여질 Url
	private String strCompanyCode; // 제조사 사용자를 위한 제조사 명
	private String strCompanyName; 
	private String strUserTypeName; 
	private String strIsNotice;
	private String strSodeLogFileServerIp;
	private int intSodeLogFileServerPort;
	private String strClientIP;
	private String strPrivateKey;
	private String strMobileSecurityKey;
	private String strRoleName;

	


	public String getStrCheckIntro() {
		return strCheckIntro;
	}

	public void setStrCheckIntro(String strCheckIntro) {
		this.strCheckIntro = strCheckIntro;
	}

	public String getStrCheckIntroSode() {
		return strCheckIntroSode;
	}

	public void setStrCheckIntroSode(String strCheckIntroSode) {
		this.strCheckIntroSode = strCheckIntroSode;
	}

	public UserInfoVo(String astrUserId) {
		this.strUserId = astrUserId;
	}
	
	public int getIntLoginResult() {
		return intLoginResult;
	}
	public void setIntLoginResult(int intLoginResult) {
		this.intLoginResult = intLoginResult;
	}
	public String getStrUserId() {
		return strUserId;
	}
	public void setStrUserId(String strUserId) {
		this.strUserId = strUserId;
	}
	public String getStrPassword() {
		return strPassword;
	}
	public void setStrPassword(String strPassword) {
		this.strPassword = strPassword;
	}
	public String getStrLoginMessage() {
		return strLoginMessage;
	}
	public void setStrLoginMessage(String strLoginMessage) {
		this.strLoginMessage = strLoginMessage;
	}
	public String getStrName() {
		return strName;
	}
	public void setStrName(String strName) {
		this.strName = strName;
	}
	public String getStrPhoneNumber() {
		return strPhoneNumber;
	}
	public void setStrPhoneNumber(String strPhoneNumber) {
		this.strPhoneNumber = strPhoneNumber;
	}
	public String getStrOrgId() {
		return strOrgId;
	}
	public void setStrOrgId(String strOrgId) {
		this.strOrgId = strOrgId;
	}
	public String getStrOrgName() {
		return strOrgName;
	}
	public void setStrOrgName(String strOrgName) {
		this.strOrgName = strOrgName;
	}
	public String getStrMobileNumber() {
		return strMobileNumber;
	}
	public void setStrMobileNumber(String strMobileNumber) {
		this.strMobileNumber = strMobileNumber;
	}
	public String getStrEmail() {
		return strEmail;
	}
	public void setStrEmail(String strEmail) {
		this.strEmail = strEmail;
	}
	public String getStrPath() {
		return strPath;
	}
	public void setStrPath(String strPath) {
		this.strPath = strPath;
	}
	public String getStrPathName() {
		return strPathName;
	}
	public void setStrPathName(String strPathName) {
		this.strPathName = strPathName;
	}
	public int getIntUserType() {
		return intUserType;
	}
	public void setIntUserType(int intUserType) {
		this.intUserType = intUserType;
	}
	public String getStrIsSMS() {
		return strIsSMS;
	}
	public void setStrIsSMS(String strIsSMS) {
		this.strIsSMS = strIsSMS;
	}
	public String getStrPathLevel() {
		return strPathLevel;
	}
	public void setStrPathLevel(String strPathLevel) {
		this.strPathLevel = strPathLevel;
	}
	public int getIntRoleId() {
		return intRoleId;
	}
	public void setIntRoleId(int intRoleId) {
		this.intRoleId = intRoleId;
	}
	public String getStrStatus() {
		return strStatus;
	}
	public void setStrStatus(String strStatus) {
		this.strStatus = strStatus;
	}
	public String getStrParentPath() {
		return strParentPath;
	}
	public void setStrParentPath(String strParentPath) {
		this.strParentPath = strParentPath;
	}
	public String getStrParentPosx() {
		return strParentPosx;
	}
	public void setStrParentPosx(String strParentPosx) {
		this.strParentPosx = strParentPosx;
	}
	public String getStrParentPosy() {
		return strParentPosy;
	}
	public void setStrParentPosy(String strParentPosy) {
		this.strParentPosy = strParentPosy;
	}
	public String getStrInitMenuUrl() {
		return strInitMenuUrl;
	}
	public void setStrInitMenuUrl(String strInitMenuUrl) {
		this.strInitMenuUrl = strInitMenuUrl;
	}
	public String getStrCompanyCode() {
		return strCompanyCode;
	}
	public void setStrCompanyCode(String strCompanyCode) {
		this.strCompanyCode = strCompanyCode;
	}
	public String getStrCompanyName() {
		return strCompanyName;
	}
	public void setStrCompanyName(String strCompanyName) {
		this.strCompanyName = strCompanyName;
	}
	public String getStrUserTypeName() {
		return strUserTypeName;
	}
	public void setStrUserTypeName(String strUserTypeName) {
		this.strUserTypeName = strUserTypeName;
	}
	public String getStrIsNotice() {
		return strIsNotice;
	}
	public void setStrIsNotice(String strIsNotice) {
		this.strIsNotice = strIsNotice;
	}
	public String getStrSodeLogFileServerIp() {
		return strSodeLogFileServerIp;
	}
	public void setStrSodeLogFileServerIp(String strSodeLogFileServerIp) {
		this.strSodeLogFileServerIp = strSodeLogFileServerIp;
	}
	public int getIntSodeLogFileServerPort() {
		return intSodeLogFileServerPort;
	}
	public void setIntSodeLogFileServerPort(int intSodeLogFileServerPort) {
		this.intSodeLogFileServerPort = intSodeLogFileServerPort;
	}
	public String getStrClientIP() {
		return strClientIP;
	}
	public void setStrClientIP(String strClientIP) {
		this.strClientIP = strClientIP;
	}
	public String getStrPrivateKey() {
		return strPrivateKey;
	}
	public void setStrPrivateKey(String strPrivateKey) {
		this.strPrivateKey = strPrivateKey;
	}
	public String getStrMobileSecurityKey() {
		return strMobileSecurityKey;
	}
	public String getStrRoleName() {
		return strRoleName;
	}

	public void setStrRoleName(String strRoleName) {
		this.strRoleName = strRoleName;
	}

	public void setStrMobileSecurityKey(String strMobileSecurityKey) {
		this.strMobileSecurityKey = strMobileSecurityKey;
	}
	
	public String getEStrName() {
		return getEString(strName, "E", 1);
	}
	
	public String getEStrEmail() {
		return getEString(strEmail, "F", 4);
	}
	
	public String getEStrMobileNumber() {
		return getEString(strMobileNumber, "E", 4);
	}
	
	private String getEString(String astrValue, String astrFlag, int intLength) {
		StringBuffer strResult = new StringBuffer();
		if (strResult == null || astrValue == null || astrValue.length() < intLength ) {
			for (int i = 0; i < intLength; i++) {
				strResult.append("*");
			}
		} else {
			if (astrFlag.equals("F")) {
				for (int i = 0; i < intLength; i++) {
					strResult.append("*");
				}
				strResult.append(astrValue.substring(intLength, astrValue.length()));
			} else if (astrFlag.equals("E")) {
				strResult.append(astrValue.substring(0, (astrValue.length() - intLength)));
				for (int i = 0; i < intLength; i++) {
					strResult.append("*");
				}
			} 
		}
		return strResult.toString();
	}
	
	public String getEncUI() {
		String strReturn = "";
		try {
			strReturn = AesCrypto.encrypt(strUserId);
		} catch (InvalidKeyException e) {
			LOG.error("", e);
		} catch (NoSuchAlgorithmException e) {
			LOG.error("", e);
		} catch (NoSuchPaddingException e) {
			LOG.error("", e);
		} catch (IllegalBlockSizeException e) {
			LOG.error("", e);
		} catch (BadPaddingException e) {
			LOG.error("", e);
		} catch (ProjectException e) {
			LOG.error("", e);
		}
		return strReturn;
	}
	
	public String checkEUserId(){
		String getUserId = this.strUserId;
		
		if(getUserId !=null){
			getUserId = getUserId.replaceAll("<", "&lt;");
			getUserId = getUserId.replaceAll(">", "&gt;");
			getUserId = getUserId.replaceAll("&", "&lt;");
			getUserId = getUserId.replaceAll("\"", "&quot;");
			getUserId = getUserId.replaceAll("eval\\((.*)\\)", "");
			getUserId = getUserId.replaceAll("[\\\"\\'][\\s]*javascript:(.*)[\\\"\\']", "\"\"");
			getUserId = getUserId.replaceAll("[\\\"\\'][\\s]*vbscript:(.*)[\\\"\\']", "\"\"");
			getUserId = getUserId.replaceAll( "document.cookie", "&#100;&#111;&#99;&#117;&#109;&#101;&#110;&#116;&#46;&#99;&#111;&#111;&#107;&#105;&#101;");
			getUserId = getUserId.replaceAll("<script", "&lt;script");
			getUserId = getUserId.replaceAll("script>", "script&gt;");
			getUserId = getUserId.replaceAll("<iframe", "&lt;iframe");
			getUserId = getUserId.replaceAll("<object", "&lt;object");
			getUserId = getUserId.replaceAll("<embed", "&lt;embed");
			getUserId = getUserId.replaceAll("onload", "no_onload");
			getUserId = getUserId.replaceAll("expression", "no_expression");
			getUserId = getUserId.replaceAll("onmouseover", "no_onmouseover");
			getUserId = getUserId.replaceAll("onmouseout", "no_onmouseout");
			getUserId = getUserId.replaceAll("onclick", "no_onclick");

		} else {
			return "";
		}
		return getEString(getUserId, "E", 3);
	}
	
	public String checkUserId(){
		String getUserId = this.strUserId;
		
		if(getUserId !=null){
			getUserId = getUserId.replaceAll("<", "&lt;");
			getUserId = getUserId.replaceAll(">", "&gt;");
			getUserId = getUserId.replaceAll("&", "&amp;");
			getUserId = getUserId.replaceAll("\"", "&quot;");
			getUserId = getUserId.replaceAll("eval\\((.*)\\)", "");
			getUserId = getUserId.replaceAll("[\\\"\\'][\\s]*javascript:(.*)[\\\"\\']", "\"\"");
			getUserId = getUserId.replaceAll("[\\\"\\'][\\s]*vbscript:(.*)[\\\"\\']", "\"\"");
			getUserId = getUserId.replaceAll( "document.cookie", "&#100;&#111;&#99;&#117;&#109;&#101;&#110;&#116;&#46;&#99;&#111;&#111;&#107;&#105;&#101;");
			getUserId = getUserId.replaceAll("<script", "&lt;script");
			getUserId = getUserId.replaceAll("script>", "script&gt;");
			getUserId = getUserId.replaceAll("<iframe", "&lt;iframe");
			getUserId = getUserId.replaceAll("<object", "&lt;object");
			getUserId = getUserId.replaceAll("<embed", "&lt;embed");
			getUserId = getUserId.replaceAll("onload", "no_onload");
			getUserId = getUserId.replaceAll("expression", "no_expression");
			getUserId = getUserId.replaceAll("onmouseover", "no_onmouseover");
			getUserId = getUserId.replaceAll("onmouseout", "no_onmouseout");
			getUserId = getUserId.replaceAll("onclick", "no_onclick");
		} else {
			return "";
		}
		return getUserId;
	}
	

	@Override
	public String toString() {
		return "UserInfoVo [intLoginResult=" + intLoginResult + ", strUserId=" + strUserId + ", strPassword="
				+ strPassword + ", strLoginMessage=" + strLoginMessage + ", strName=" + strName + ", strPhoneNumber="
				+ strPhoneNumber + ", strOrgId=" + strOrgId + ", strOrgName=" + strOrgName + ", strMobileNumber="
				+ strMobileNumber + ", strEmail=" + strEmail + ", strPath=" + strPath + ", strPathName=" + strPathName
				+ ", intUserType=" + intUserType + ", strIsSMS=" + strIsSMS + ", strPathLevel=" + strPathLevel
				+ ", intRoleId=" + intRoleId + ", strCheckIntro=" + strCheckIntro
				+ ", strCheckIntroSode=" + strCheckIntroSode + ", strStatus=" + strStatus + ", strParentPath="
				+ strParentPath + ", strParentPosx=" + strParentPosx + ", strParentPosy=" + strParentPosy
				+ ", strInitMenuUrl=" + strInitMenuUrl + ", strCompanyCode=" + strCompanyCode + ", strCompanyName="
				+ strCompanyName + ", strUserTypeName=" + strUserTypeName + ", strIsNotice=" + strIsNotice
				+ ", strSodeLogFileServerIp=" + strSodeLogFileServerIp + ", intSodeLogFileServerPort="
				+ intSodeLogFileServerPort + ", strClientIP=" + strClientIP + ", strPrivateKey=" + strPrivateKey
				+ ", strMobileSecurityKey=" + strMobileSecurityKey + ", strRoleName=" + strRoleName + "]";
	}
	
}
