package com.my.base.exception;

public class ValidatorException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String code;
	private String msg;
	
	public ValidatorException(String msg) {
		super(msg);
		this.code = null;
		this.msg = msg;
	}
	
	public ValidatorException(String code, String msg) {
		super(msg);
		this.code = code;
		this.msg = msg;
	}
	
	public ValidatorException(String msg, Exception ex) {
		super(ex);
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "ValidatorException [code=" + code + ", msg=" + msg + "]";
	}
}
