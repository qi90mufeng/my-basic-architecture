/**
 * sinafenqi.com
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.my.first.back.web;

import com.my.first.client.web.MovieWebFeignClient;
import org.springframework.stereotype.Component;

/**
 * 服务降级
 */
@Component
public class MovieWebFeignHystric implements MovieWebFeignClient {

    @Override
    public String hi(String customerId) {
        return "hi," + customerId + " web service error";
    }
}
