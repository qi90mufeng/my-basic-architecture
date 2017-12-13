package com.my.second.controller;

import com.my.second.client.web.MovieWebFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/second-web")
public class MovieWebController {

    @Autowired
    private MovieWebFeignClient movieWebFeignClient;

    @RequestMapping(value = "/hi/{id}", method = RequestMethod.GET)
    public String hi(@PathVariable("id") String customerId) {
        return movieWebFeignClient.hi(customerId);
    }
}
