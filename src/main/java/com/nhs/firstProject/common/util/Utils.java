package com.nhs.firstProject.common.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nhs.firstProject.common.exception.ProjectException;



public class Utils {
	private static final Logger LOG = LoggerFactory.getLogger(Utils.class);
	
	// 공지용 Html빌드 호출
	public static String buildMailHtmlString(String serverURL, Map<String, Object> param, String division) throws ProjectException {
		
		StringBuffer sb = new StringBuffer();
		String strRtnMail = "";
		try {
			Date dtNow = new Date();
			SimpleDateFormat dfFmtNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String strRegDT = URLEncoder.encode(dfFmtNow.format(dtNow), "EUC-KR");
			
			String strTitle = param.get("Title") == null ? "" : URLEncoder.encode((String) param.get("Title"), "UTF-8");
			String strWriterName = param.get("WriterName") == null ? "UCEMS" : URLEncoder.encode((String) param.get("WriterName"), "UTF-8");
			String strContents = param.get("Contents") == null ? "" : (String) param.get("Contents");
			
			String strAttach1 = param.get("UpFileName1") == null ? "" : URLEncoder.encode((String) param.get("UpFileName1"), "UTF-8");
			String strAttach2 = param.get("UpFileName2") == null ? "" : URLEncoder.encode((String) param.get("UpFileName2"), "UTF-8");
			String strAttachV1 = param.get("UpFileVName1") == null ? "" : URLEncoder.encode((String) param.get("UpFileVName1"), "UTF-8");
			String strAttachV2 = param.get("UpFileVName2") == null ? "" : URLEncoder.encode((String) param.get("UpFileVName2"), "UTF-8");
			
			String _url = serverURL + "/NoticeMailBuild.do?ActionFlag=C&Title=" + strTitle + "&WriterName=" + strWriterName + "&Contents=" + "&RegDT=" + strRegDT + "&UpFileName1=" + strAttach1 + "&UpFileName2=" + strAttach2 + "UpFileVName1=" + strAttachV1 + "&UpFileVName2=" + strAttachV2;
			
			URL httpUrl = new URL(_url);
			URLConnection conn = httpUrl.openConnection();
			HttpURLConnection httpConn = (HttpURLConnection) conn;
			httpConn.connect();
			
			// 리턴된 페이지의 내용을 스트링으로 변환한다.
			InputStream is = httpConn.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while ((line = in.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}
			
			//2010.11.16 : HTML로 표현시 Flex의 htmltext의 <font> 태그의 크기가 다르게 된다. -> 플렉스 화면에서는 '12', '20', '30'으로만 입력 가능하다.
			//HTML로 표현시 적용되도록 크기 변경해줌
			strContents = strContents.replaceAll("SIZE='12'", "SIZE='2'");
			strContents = strContents.replaceAll("SIZE='20'", "SIZE='3'");
			strContents = strContents.replaceAll("SIZE='30'", "SIZE='4'");
			
			// 메일 내용은 여기서 설정해 준다.
			strRtnMail = sb.toString();
			strRtnMail = strRtnMail.replace("@dynamicContents@", strContents);
			
			LOG.info("공지메일 발송 요청함 : " + strTitle);
		} catch (Exception ex) {
			return "";
		}
		return strRtnMail;
	}
}
