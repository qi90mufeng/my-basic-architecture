/**
 * sinafenqi.com
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.my.first.client.app;

import com.my.first.back.app.MovieAppFeignFallBack;
import com.my.first.definition.MovieAppDefinition;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name = "first-service", path = "/movie", fallback = MovieAppFeignFallBack.class)
public interface MovieAppFeignClient extends MovieAppDefinition{

}