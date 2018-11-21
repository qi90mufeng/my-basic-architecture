package com.my.base.constant;

import org.apache.commons.lang.StringUtils;

/**
 * 用户类型枚举
 */
public enum UserTypeEnum {

    /** 1, APP端客户 */
    CUSTOMER("customer", "APP端客户"),

    /** 2, APP端商户 */
    MERCHANT("merchant", "APP端商户"),

    /** 3, 后台用户 */
    USER("user", "后台用户"),

    /** 4, 白名单 */
    WHITELIST("whitelist", "白名单");

    private String code;

    private String name;

    private UserTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getNameByCode(String code) {

        for (UserTypeEnum userTypeEnum : UserTypeEnum.values()) {
            if (StringUtils.equals(userTypeEnum.getCode(), code)) {
                return userTypeEnum.getName();
            }
        }
        return null;
    }

    public static UserTypeEnum getEnumByCode(String code) {

        for (UserTypeEnum userTypeEnum : UserTypeEnum.values()) {
            if (StringUtils.equals(userTypeEnum.getCode(), code)) {
                return userTypeEnum;
            }
        }
        return null;
    }

}
