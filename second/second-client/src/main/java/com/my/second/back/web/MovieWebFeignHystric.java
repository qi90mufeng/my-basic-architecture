package com.my.second.back.web;

import com.my.second.client.web.MovieWebFeignClient;
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
