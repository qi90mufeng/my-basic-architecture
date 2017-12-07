/**
 * sinafenqi.com
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.my.first.client.app;

import com.my.first.back.app.MoviceFeignFallBack;
import com.my.first.definition.MoviceAppDefinition;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name = "first_service", path = "/movice", fallback = MoviceFeignFallBack.class)
public interface MoviceAppFeignClient extends MoviceAppDefinition{

}