/**
 * sinafenqi.com
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.my.first.client.app;

import com.my.first.back.app.MovieAppFeignHystric;
import com.my.first.definition.MovieResourceDefinition;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name = "first-service", path = "/movie", fallback = MovieAppFeignHystric.class)
public interface MovieAppFeignClient extends MovieResourceDefinition {

}