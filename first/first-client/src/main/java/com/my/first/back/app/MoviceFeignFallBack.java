/**
 * sinafenqi.com
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.my.first.back.app;

import com.my.first.client.app.MoviceAppFeignClient;

/**
 * 服务降级
 */
public class MoviceFeignFallBack implements MoviceAppFeignClient{

    @Override
    public String hi(String customerId) {
        return "hi, service error";
    }
}
