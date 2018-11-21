package com.my.first.client.web;

import com.my.first.back.web.MovieWebFeignHystric;
import com.my.first.definition.MovieResourceDefinition;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name = "first-service", path = "/movie", fallback = MovieWebFeignHystric.class)
public interface MovieWebFeignClient extends MovieResourceDefinition{

}