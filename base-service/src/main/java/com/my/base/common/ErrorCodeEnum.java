package com.my.base.common;

public enum ErrorCodeEnum {

	/** ----------------------公共错误码 --------------------------*/
	SYS_ERROR("9999", "系统错误"),
	ILLEGAL_OPER("9998", "非法操作"),
	ILLEGAL_PARAM("9997", "非法参数:%s"),
	SYS_BUSY("9996", "系统繁忙"),
	DICT_ENUM_ERROR("9995","错误的字典类型"),
	;

	private String code;
	private String msg;

	private ErrorCodeEnum(String code, String msg) {
		this.code = code;
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
}
