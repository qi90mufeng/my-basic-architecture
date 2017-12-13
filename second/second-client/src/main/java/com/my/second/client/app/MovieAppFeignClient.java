package com.my.second.client.app;

import com.my.first.definition.MovieResourceDefinition;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name = "second-service", path = "/second")
public interface MovieAppFeignClient extends MovieResourceDefinition {

}