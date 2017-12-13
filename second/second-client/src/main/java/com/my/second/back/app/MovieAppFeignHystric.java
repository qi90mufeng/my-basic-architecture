package com.my.second.back.app;

import com.my.second.client.app.MovieAppFeignClient;
import org.springframework.stereotype.Component;

/**
 * 服务降级
 */
@Component
public class MovieAppFeignHystric implements MovieAppFeignClient{

    @Override
    public String hi(String customerId) {
        return "hi," + customerId + "app service error";
    }
}
