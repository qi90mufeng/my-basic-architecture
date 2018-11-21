package com.my.base.exception;

import com.my.base.common.ErrorCodeEnum;
import com.my.base.dto.ResultDTO;

public class BizException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private String msg;
	
	public BizException(Exception ex) {
		super(ex);
	}
	
	public BizException(ResultDTO result) {
		super(result.getMsg());
		this.code = result.getCode();
		this.msg = result.getMsg();
	}
	
	public BizException(String code, String msg) {
		super(msg);
		this.code = code;
		this.msg = msg;
	}
	
	public BizException(ErrorCodeEnum errorCode) {
		super(errorCode.getMsg());
		this.code = errorCode.getCode();
		this.msg = errorCode.getMsg();
	}
	
	public BizException(String code, String msg, Exception ex) {
		super(ex);
		this.code = code;
		this.msg = msg;
	}

	public BizException(ErrorCodeEnum errorCode, String extraMsg) {
		super(extraMsg);
		this.code = errorCode.getCode();
		this.msg = String.format(errorCode.getMsg(), extraMsg);
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
	public synchronized Throwable fillInStackTrace() {
		return this;
	}

	@Override
	public String toString() {
		return "BizException [code=" + code + ", msg=" + msg + "]";
	}
}
