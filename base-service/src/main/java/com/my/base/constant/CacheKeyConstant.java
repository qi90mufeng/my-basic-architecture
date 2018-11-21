package com.my.base.constant;

/**
 * 缓存key前缀常量
 */
public class CacheKeyConstant {

    /** 客户系统缓存前缀 */
    private static final String CUSTOMER_PREFIX = "crm|";
    /** 客户ID关联token key，用于顶掉上一个登陆的用户<br/>this + customerId */
    public static final String  CUST_TO_TOKEN   = CUSTOMER_PREFIX + "custToToken";

    private CacheKeyConstant() {

    }

}
