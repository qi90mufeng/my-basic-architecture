package com.my.base.dto.response;

import java.io.Serializable;

public class NotPermitResponse implements Serializable {

	private static final long serialVersionUID = -1061694697432548747L;

	private String code;

	private String Description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

}
