/**
 * sinafenqi.com
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.my.first.back.app;

import com.my.first.client.app.MovieAppFeignClient;
import org.springframework.stereotype.Component;

/**
 * 服务降级
 */
@Component
public class MovieAppFeignFallBack implements MovieAppFeignClient{

    @Override
    public String hi(String customerId) {
        return "hi, service error";
    }
}
