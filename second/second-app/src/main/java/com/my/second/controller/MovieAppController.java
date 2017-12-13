package com.my.second.controller;

import com.my.second.client.app.MovieAppFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/second-app")
public class MovieAppController {

    @Autowired
    private MovieAppFeignClient movieAppFeignClient;

    @RequestMapping(value = "/hi/{id}", method = RequestMethod.POST)
    public String hi(@PathVariable("id") String customerId) {
        return "";
    }
}
