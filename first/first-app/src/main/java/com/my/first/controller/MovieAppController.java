package com.my.first.controller;

import com.my.first.client.app.MovieAppFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/first-app")
public class MovieAppController{

    @Autowired
    private MovieAppFeignClient movieAppFeignClient;

    @RequestMapping(value = "/hi/{id}", method = RequestMethod.GET)
    public String hi(@PathVariable("id") String customerId) {
        return movieAppFeignClient.hi(customerId);
    }

}
