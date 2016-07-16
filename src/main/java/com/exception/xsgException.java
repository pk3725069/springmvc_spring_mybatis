package com.exception;



public class xsgException extends RuntimeException{


	private static final long serialVersionUID = 1L;
	/**
	 *用于保存附加数据信息 
	 */
	public Object attachInfo = null;	
	
	public xsgException(Exception e) {
		super(e);
	}
	public xsgException(Exception e, Object obj) {		
		super(e);
		this.attachInfo = obj;		
	}
	public xsgException(String message) {
		super(message);
	}

	public xsgException(String message, Exception e) {
		super(message, e);
	}

	/**
	 * @return the attachInfo
	 */
	public Object getAttachInfo() {
		return attachInfo;
	}
	/**
	 * @param attachInfo the attachInfo to set
	 */
	public void setAttachInfo(Object attachInfo) {
		this.attachInfo = attachInfo;
	}
}
