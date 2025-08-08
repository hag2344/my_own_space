package com.nhs.firstProject.common.util;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

/**
 * 
 * 경로조작 방어를 위한 파일명 필터링
 * 
 */
public class FileValidCheck {
	//HTTP 응답분할 CR,LF 필터링
	public static String getValidFileName(String fileName) {
		String newFileName = fileName.replaceAll("\r", "").replaceAll("\n", "");	
	    if(newFileName.length()==0){
	    	throw new IllegalStateException("File Name " + fileName + " results in a empty fileName!");
	    }
	  //  System.out.println("RETURN : " + newFileName);
	    return newFileName;
	}
	
	//경로조작 및 자원 삽입 필터링
	public static String getPathManipulationValidFileName(String fileName) {
		String newFileName = fileName.replace("^\\.+", "").replaceAll("[\\\\/:*=%'?\"<>|]", "");
	    if(newFileName.length()==0){
	    	throw new IllegalStateException("File Name " + fileName + " results in a empty fileName!");
	    }
	//    System.out.println("RETURN : " + newFileName);
	    return newFileName;
	}
	
	public static String getValidRoot(String fileRoot){
		
		if(fileRoot == null){
			return null;
		}
		String cleanString = "";
		for(int i = 0; i<fileRoot.length(); ++i){
			cleanString += cleanRootChar(fileRoot.charAt(i));
		}
		return cleanString;
	}
	
	public static String getValidPath(String filePath){
		
		if(filePath == null){
			return null;
		}
		String cleanString = "";
		for(int i = 0; i<filePath.length(); ++i){
			cleanString += cleanPathChar(filePath.charAt(i));
		}
		return cleanString;
	}

	//root
	private static char cleanRootChar(char aChar) {
		
		//'A'-'Z'
		for (int i = 65; i < 91; ++i) {
			if (aChar == i){
				return (char)i;
			}
		}
		//'a'-'z'
		for (int i = 97; i < 123; ++i) {
			if (aChar == i){
				return (char) i; 
			}
		}
		return getRootSpecialLetter(aChar);
	}

	//root
	private static char getRootSpecialLetter(char aChar) {
		switch (aChar) {
			case ':': 
				return ':';
			case '\\': 
				return '\\';
			case '-':
				return '-';
			default:
				return 0;
		}
	}
	
	//path
	private static char cleanPathChar(char aChar) {
		for (int i = 48; i < 58; ++i) {
			if (aChar == i){
				return (char) i; 
			} 
		}
		
		//'A'-'Z'
		for (int i = 65; i < 91; ++i) {
			if (aChar == i){
				return (char)i;
			}
		}
		//'a'-'z'
		for (int i = 97; i < 123; ++i) {
			if (aChar == i){
				return (char) i; 
			}
		}
		return getPathSpecialLetter(aChar);
	}

	//path
	private static char getPathSpecialLetter(char aChar) {
		switch (aChar) {
			case '/': 
				return '/';
			case '\\':
				return '\\';
			case ':' :
				return ':';
			default:
				return 0;
		}
	}
	
	
}
