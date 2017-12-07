/**
 * sinafenqi.com
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.my.first.client.web;

import com.my.first.definition.MoviceWebDefinition;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name = "first_service", path = "/movice")
public interface MoviceWebFeignClient extends MoviceWebDefinition{

}