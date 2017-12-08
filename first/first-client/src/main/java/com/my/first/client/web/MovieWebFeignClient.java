/**
 * sinafenqi.com
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.my.first.client.web;

import com.my.first.back.web.MovieWebFeignFallBack;
import com.my.first.definition.MovieWebDefinition;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name = "first-service", path = "/movie", fallback = MovieWebFeignFallBack.class)
public interface MovieWebFeignClient extends MovieWebDefinition{

}