package com.nhs.firstProject.common.exception;

public class ProjectException extends Exception{ 
	
	private static final long serialVersionUID = 1L;

	public ProjectException(String strMessage, Exception ex) {
		super(strMessage, ex);
	}
	
	public ProjectException(String strMessage) {
		super(strMessage);
	}
	
	public ProjectException(Exception aE) {
		super(aE);
	}  
}
