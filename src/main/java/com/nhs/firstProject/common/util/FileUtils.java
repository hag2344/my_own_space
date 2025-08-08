package com.nhs.firstProject.common.util;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.nhs.firstProject.common.exception.ProjectException;

@Controller
@RequestMapping(value="/file")
@PropertySource("classpath:config/properties/linkage.properties")
public class FileUtils {
	
	private static final Logger LOG = LoggerFactory.getLogger(FileUtils.class);
	
	@Value("#{environment['mcastWebService.endpoint']}")
	private String mcastWebServiceEndPoint;
	

	private static String fileUploadFilePath;
	@Value("#{environment['fileUpload.filePath']}")
	private void fileUploadSetValue(String orgValue) {
		fileUploadFilePath = orgValue;
	}
	
	
	/**
	 * @author : nhs
	 * @Date : 2025. 02. 23.
	 * @Method 설명 : 파일 삭제 (파일 풀 경로)
	 */
	public static ModelAndView fullPathFileDelete(String realFileNamePath) throws ProjectException {
		LOG.info("===========>fullPathFileDelete Start {}");
		try {
				File file = new File(realFileNamePath);
				file.delete();	
			
		} catch (Exception ex) {
			LOG.error("fullPathFileDelete Error :: {}", ex);
			throw new ProjectException("파일 삭제시 Error가 발생했습니다.", ex);
		}
		LOG.info("fileDelete End <============");
		return null;
	}
	
	
	/**
	 * @author : nhs
	 * @Date : 2025. 02. 23.
	 * @Method 설명 : 파일 다운로드 (파일 풀 경로)
	 */
	@RequestMapping(value = "/fullPathFileDown.do", method = {RequestMethod.GET, RequestMethod.POST})
	public static ModelAndView fullPathFileDown(@RequestParam Map<String, Object> paramData , HttpServletResponse response, HttpServletRequest request) throws ProjectException {
		LOG.info("===========>fullPathFileDown Start");
		try {
			// 파일을 저장했던 위치에서 첨부파일을 읽어 byte[]형식으로 변환한다.
			String fileName = "";
			String filePath = "" ;
			fileName = paramData.get("FILE_NAME").toString();
			filePath = paramData.get("FILE_PATH").toString();
			LOG.info("Download FileName ==> {}", filePath);
			byte fileByte[] = null; 
			fileByte = org.apache.commons.io.FileUtils.readFileToByteArray(new File(filePath));
			
			response.setContentType("application/octet-stream");
			if (null != fileByte) {
				response.setContentLength(fileByte.length);
			} else {
				response.setContentLength(0);
			}
			response.setHeader("Content-Disposition",  "attachment; fileName=\""+URLEncoder.encode(URLDecoder.decode(fileName, "UTF-8"), "UTF-8")+"\";");
			response.getOutputStream().write(fileByte);
			response.getOutputStream().flush();
			response.getOutputStream().close();

		} catch (Exception ex) {
			LOG.error("fullPathFileDown Error :: {}", ex);
			throw new ProjectException("파일 다운로드시 Error가 발생했습니다.", ex);
		}
		LOG.info("fileDown End <============");
		return null;
	}
	
	/**
	 * @author : nhs
	 * @Date : 2025. 02. 23.
	 * @Method 설명 : 파일 업로드(파라미터타입 : MultipartHttpServletRequest) (현재 이 부분 파일 업로드 사용)
	 */
	public static Map<String, Object> fileUpload(MultipartHttpServletRequest mpRequest) throws ProjectException {
		LOG.info("===========>fileUpload Start");
		Map<String, Object> resultData = new HashMap<String, Object>();
		List<String> realFileNameList = new ArrayList<String>();
		try {
			Iterator<String> iterator = mpRequest.getFileNames();
			MultipartFile multipartFile = null;
			String originalFileName = null;
			String originalFileExtension = null;
			String realFileName = null;
			String serverFilePath = null;
			
			if (mpRequest.getServletPath().contains("getUploadList.do")) {
				serverFilePath = fileUploadFilePath;
			} 
			
			File file = new File(serverFilePath);
			if(file.exists() == false) {
				file.mkdirs();
			}
			
			while(iterator.hasNext()) {
				multipartFile = mpRequest.getFile(iterator.next());
				if(multipartFile.isEmpty() == false) {
					originalFileName = multipartFile.getOriginalFilename();
					originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
//					realFileName = GlobalUtil.getEncMD5(originalFileName);
					realFileName = originalFileName;
					realFileNameList.add(realFileName);
					LOG.info("Upload File : {} ==> {}", originalFileName, serverFilePath + realFileName);
					file = new File(serverFilePath + realFileName);
					if(file.exists() == false) {
						multipartFile.transferTo(file);
					}
					
				}
			}
			resultData.put("realFileNameList", realFileNameList);
			resultData.put("msg", "success");
		} catch (Exception ex) {
			LOG.error("fileUpload Error :: {}", ex);
			resultData.put("msg", "error");
			return null;
		}
		LOG.info("fileUpload End <============");
		return resultData;
	}
	
	
	/**
	 * @author : nhs
	 * @Date : 2025. 02. 23.
	 * @Method 설명 : 파일 삭제
	 */
	public static ModelAndView fileDelete(List<String> realFileNameList) throws ProjectException {
		LOG.info("===========>fileDelete Start {}");
		try {
			String filePath = null;
			for(String realFileName:realFileNameList) {
				if(realFileName != null) {
					File file = new File(filePath + realFileName);
					file.delete();
				}
			}
		} catch (Exception ex) {
			LOG.error("fileDelete Error :: {}", ex);
			throw new ProjectException("파일 삭제시 Error가 발생했습니다.", ex);
		}
		LOG.info("fileDelete End <============");
		return null;
	}
	
	/**
	 * @author : nhs
	 * @Date : 2025. 02. 23.
	 * @Method 설명 : 파일 다운로드
	 */
	@RequestMapping(value = "/fileDown.do", method = {RequestMethod.GET, RequestMethod.POST})
	public static ModelAndView fileDown(String fileName, String realFileName, HttpServletResponse response, HttpServletRequest request) throws ProjectException {
		LOG.info("===========>fileDown Start");
		try {
			String filePath = null;
			// 파일을 저장했던 위치에서 첨부파일을 읽어 byte[]형식으로 변환한다.
			String serverFilePath = filePath;
			if (null != request.getParameter("svRp") && request.getParameter("svRp").equals("apkuploadreference")) {
				serverFilePath = fileUploadFilePath;
			} 
		
			LOG.info("Download FileName ==> {}", serverFilePath+realFileName);
			byte fileByte[] = null; 
			fileByte = org.apache.commons.io.FileUtils.readFileToByteArray(new File(serverFilePath+FileValidCheck.getValidFileName(realFileName)));
			
			response.setContentType("application/octet-stream");
			if (null != fileByte) {
				response.setContentLength(fileByte.length);
			} else {
				response.setContentLength(0);
			}
			response.setHeader("Content-Disposition",  "attachment; fileName=\""+URLEncoder.encode(URLDecoder.decode(fileName, "UTF-8"), "UTF-8")+"\";");
			response.getOutputStream().write(fileByte);
			response.getOutputStream().flush();
			response.getOutputStream().close();

		} catch (Exception ex) {
			LOG.error("fileDown Error :: {}", ex);
			throw new ProjectException("파일 다운로드시 Error가 발생했습니다.", ex);
		}
		LOG.info("fileDown End <============");
		return null;
	}
	
}	
