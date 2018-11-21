package com.my.base.constant;

import org.apache.commons.lang.StringUtils;

/**
 * @（#）:SmsTemplateEnum.java
 * @description:
 * @version: Version 1.0
 */
public enum SmsTemplateEnum {

    /** 注册验证码 -【新浪分期】您申请的手机动态码${captcha}，有效期5分钟；如非本人操作，请与客服联系；联系电话：400-104-5888*/
    REGISTER_CODE("SMS_00000", "注册验证码"),

    /** 登陆验证码 -【新浪分期】您本次登陆的验证码为：${captcha}，有效期5分钟；如非本人操作，请与客服联系；联系电话：400-104-5888 */
    LOGIN_CODE("SMS_00000", "登陆验证码"),

    // TODO 需要确认模板，以及是否需要填充参数
    RESET_PSW_CODE("SMS_00000", "重置登录密码"),

    // TODO 需要确认模板，以及是否需要填充参数
    RESET_PAY_PSW_CODE("SMS_00000", "重置交易密码"),

    ;

    private String code;

    private String name;

    private SmsTemplateEnum(String code, String name) {
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

        for (SmsTemplateEnum smsTemplateEnum : SmsTemplateEnum.values()) {
            if (StringUtils.equals(smsTemplateEnum.getCode(), code)) {
                return smsTemplateEnum.getName();
            }
        }
        return null;
    }

    public static SmsTemplateEnum getEnumByCode(String code) {

        for (SmsTemplateEnum smsTemplateEnum : SmsTemplateEnum.values()) {
            if (StringUtils.equals(smsTemplateEnum.getCode(), code)) {
                return smsTemplateEnum;
            }
        }
        return null;
    }
}
