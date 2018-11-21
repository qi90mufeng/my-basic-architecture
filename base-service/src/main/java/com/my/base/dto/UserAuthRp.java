package com.my.base.dto;

import java.io.Serializable;

/**
 * 用户权限返回对象
 *
 */
public class UserAuthRp implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 权限id */
    private String            authId;

    /** 权限地址 */
    private String            authUrl;

    /** 请求类型 */
    private String            requestType;

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getAuthUrl() {
        return authUrl;
    }

    public void setAuthUrl(String authUrl) {
        this.authUrl = authUrl;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    @Override
    public String toString() {
        return "UserAuthRp [authId=" + authId + ", authUrl=" + authUrl + ", requestType="
               + requestType + "]";
    }
}