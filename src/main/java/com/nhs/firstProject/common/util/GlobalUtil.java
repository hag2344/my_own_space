package com.nhs.firstProject.common.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;

public class GlobalUtil {

	public GlobalUtil() {
	}

	public static final String SDF_FORMAT_SECOND = "yyyy.MM.dd HH:mm:ss";
	public static final String SDF_FORMAT_DETAIL = "yyyy.MM.dd HH:mm";
	public static final String SDF_FORMAT_SIMPLE = "yyyy.MM.dd";
	private static final Logger LOG = LoggerFactory.getLogger(GlobalUtil.class);  
	
	public static String getFileName(String filename, HttpServletRequest request) {
		String browser = getBrowser(request);
		String dispositionPrefix = "attachment; filename=";
		String encodedFilename = null;
		try {
			if (browser.equals("MSIE")) {
				encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
			} else if (browser.equals("Firefox")) {
				encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
			} else if (browser.equals("Opera")) {
				encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
			} else if (browser.equals("Chrome")) {
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < filename.length(); i++) {
					char c = filename.charAt(i);
					if (c > '~') {
						sb.append(URLEncoder.encode("" + c, "UTF-8"));
					} else {
						sb.append(c);
					}
				}
				encodedFilename = sb.toString();
			} else {
				throw new IOException("Not supported browser");
			}
		} catch (Exception e) {
			LOG.error("getFileName {}", e);
		}

		return dispositionPrefix + encodedFilename;
	}

	public static String getBrowser(HttpServletRequest request) {
		String header = request.getHeader("User-Agent");
		if (header.indexOf("Firefox") > -1) {
			return "Firefox";
		} else if (header.indexOf("Chrome") > -1) {
			return "Chrome";
		} else if (header.indexOf("Opera") > -1) {
			return "Opera";
		} else {
			return "MSIE";
		}
	}

	public static String[] jsonArrayToStringArray(JSONArray array) {
		if (array == null) {
			return null;
		}
		String[] arr = new String[array.size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = array.optString(i);
		}
		return arr;
	}

	public static Map<String, Object> convertJSONstringToMap(String json) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		map = mapper.readValue(json, new TypeReference<Map<String, Object>>() {
		});
		// map = mapper.readValue(json, new TypeReference<Map<String, Object>>() {});

		return map;
	}

	public static String getTimeWithFormat(Date dDate, long aintTermDate, String strFlag) {
		SimpleDateFormat sdf = new SimpleDateFormat(strFlag, Locale.KOREA);
		String strReturn = "";
		Date d = null;
		
		if (dDate == null) {
			d = new Date();
		} else {
			d = dDate;
		}
		d.setTime(d.getTime() - (aintTermDate * 24 * 60 * 60));
		strReturn = sdf.format(d);
		return strReturn;
		
	}
	
	/**
	 * @Method 설명 : UUID를 구한다.
	 */
	public static UUID getUUID() {
		UUID strUuid = UUID.randomUUID();
		return strUuid;
	}
	
	/**
	 * @Method 설명 : UUID + 확장자로 구성된 파일명을 리턴한다. 
	 */
	public static String makeRealFileName(String astrFileName) {
		String strReturn = astrFileName;
		strReturn = getUUID()+astrFileName.substring(astrFileName.lastIndexOf("."));
		return strReturn;
	}
	
	/**
	 * 문자열을 MD-5 방식으로 암호화
	 * @param txt 암호화 하려하는 문자열
	 * @return String
	 * @throws Exception
	 */
	public static String getEncMD5(String txt) throws Exception {
	     
	    StringBuffer sbuf = new StringBuffer();
	          
	    MessageDigest mDigest = MessageDigest.getInstance("MD5");
	    mDigest.update(txt.getBytes());
	     
	    byte[] msgStr = mDigest.digest() ;
	     
	    for(int i=0; i < msgStr.length; i++){
	        String tmpEncTxt = Integer.toHexString((int)msgStr[i] & 0x00ff) ;          
	        sbuf.append(tmpEncTxt) ;
	    }
	     
	    return sbuf.toString() ;
	}
	
	public static String md(String a) throws Exception{
	    MessageDigest md = MessageDigest.getInstance("MD5");
	    FileInputStream fis = new FileInputStream(a);
	    
	    byte[] dataBytes = new byte[1024];
	 
	    int nread = 0; 
	    while ((nread = fis.read(dataBytes)) != -1) {
	      md.update(dataBytes, 0, nread);
	    }
	    byte[] mdbytes = md.digest();
	 
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < mdbytes.length; i++) {
	      sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
	    }
	 
	    return sb.toString();
	   
	    }
		

}
