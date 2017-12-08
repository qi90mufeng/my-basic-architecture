/**
 * sinafenqi.com
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.my.first.controller;

import com.my.first.client.app.MovieAppFeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/first-app")
public class MovieAppController{

    @RequestMapping(value = "/hi/{id}", method = RequestMethod.POST)
    public String hi(@RequestHeader("id") String customerId) {
        return "";
    }

}
