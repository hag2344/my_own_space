package com.nhs.firstProject.common.vo;

public class LoginResult {
	
	private java.lang.String result;

    private int failCode;

    private int exCode;
    
    

    private java.lang.String failReason;
   
    private java.lang.String msgTitle;
    

	public java.lang.String getResult() {
		return result;
	}

	public void setResult(java.lang.String result) {
		this.result = result;
	}

	public int getFailCode() {
		return failCode;
	}

	public void setFailCode(int failCode) {
		this.failCode = failCode;
	}

	public int getExCode() {
		return exCode;
	}

	public void setExCode(int exCode) {
		this.exCode = exCode;
	}

	public java.lang.String getFailReason() {
		return failReason;
	}

	public void setFailReason(java.lang.String failReason) {
		this.failReason = failReason;
	}

	public java.lang.String getMsgTitle() {
		return msgTitle;
	}

	public void setMsgTitle(java.lang.String msgTitle) {
		this.msgTitle = msgTitle;
	}
	

}
