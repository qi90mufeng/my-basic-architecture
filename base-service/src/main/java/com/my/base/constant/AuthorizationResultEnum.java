package com.my.base.constant;

public enum AuthorizationResultEnum {

    PERMIT("A000", "权限检验通过"),

    OVERTIME("A001", "Token已完全过期失效，需重新登录"),

    NOTPERMIT("A002", "权限不足"),

    ERRORCAUSE("A003", "权限检验时出现异常"),

    NOTOKEN("A004", "未从请求中取得Token"),

    WRONGINFO("A005", "客户信息错误"),

    SYSTEMCAUSE("A006", "系统出现异常"),
    
    LOGIN_ANOTHER("A007", "异处登录");

    private String description;

    private String code;

    private AuthorizationResultEnum(String code, String description) {
        this.description = description;
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
