package com.my.base.common;

import java.util.ArrayList;
import java.util.List;

public enum DictEnum {
	/*****  币种   *****/
	CUSTOMERACCOUNT_CURRENCY_RMB(DictTypes.CUSTOMERACCOUNT_CURRENCY, "0001", "人民币"),
	CUSTOMERACCOUNT_CURRENCY_USD(DictTypes.CUSTOMERACCOUNT_CURRENCY, "0002", "美元"),
	
    /***** 设备类型  *****/
    ANDROID(DictTypes.DEVICE_TYPE,"0001","安卓",1),
    IOS(DictTypes.DEVICE_TYPE,"0002","IOS",2),

	/***** 用户类型 *****/
	APP_CUSTOMER(DictTypes.USER_TYPE, "0001", "APP端客户", 1),
	APP_MERCHANT(DictTypes.USER_TYPE, "0002", "APP端商户", 2),
	BACKSTAGE_USER(DictTypes.USER_TYPE, "0003", "后台用户", 3),
	ADMIN(DictTypes.USER_TYPE, "0004", "后台管理员", 4),
	;

	private String type;
	private String code;
	private String value;
	private Integer index;
	
	private DictEnum(String type, String code, String value) {
	    this.type = type;
        this.code = code;
        this.value = value;
	}
	
	private DictEnum(String type, String code, String value,Integer index) {
	    this.type = type;
        this.code = code;
        this.value = value;
        this.index = index;
    }
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public static List<DictEnum> getDictList(String type) {
		List<DictEnum> result = new ArrayList<>();
		DictEnum[] dicts = DictEnum.values();
		for(DictEnum dict : dicts) {
			if (dict.getType()!=null && dict.getType().equals(type)) {
				result.add(dict);
			}
		}
		return result;
	}
    
	
	public static DictEnum getDict(String type,String code){
		List<DictEnum> list = getDictList(type);
		for(DictEnum dict : list) {
			if (dict.getCode().equals(code)) {
				return dict;
			}
		}
		return null;
	}
	
	public static String getDictCode(String type,String value){
		List<DictEnum> list = getDictList(type);
		for(DictEnum dict : list) {
			if (dict.getValue().equals(value)) {
				return dict.getCode();
			}
		}
		return null;
	}

	public static String getDictCode(String type, Integer index) {
		List<DictEnum> list = getDictList(type);
		for (DictEnum dict : list) {
			if (dict.getIndex() == index) {
				return dict.getCode();
			}
		}
		return null;
	}

	public static String getDictValue(String code, String dictType) {
		DictEnum dict = getDict(dictType, code);
		if (dict != null) {
			return dict.getValue();
		}
		return null;
	}

    public static String getDictValue(String type, Integer index) {
        List<DictEnum> list = getDictList(type);
        for (DictEnum dict : list) {
            if (dict.getIndex() == index) {
                return dict.getValue();
            }
        }
        return null;
    }
}
