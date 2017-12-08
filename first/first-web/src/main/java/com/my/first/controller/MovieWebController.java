/**
 * sinafenqi.com
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.my.first.controller;

import com.my.first.client.web.MovieWebFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/first-web")
public class MovieWebController {

    @Autowired
    private MovieWebFeignClient movieWebFeignClient;

    @RequestMapping(value = "/hi/{id}", method = RequestMethod.GET)
    public String hi(@PathVariable("id") String customerId) {
        return movieWebFeignClient.hi(customerId);
    }
}
