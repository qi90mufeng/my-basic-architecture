package com.my.second.controller;

import com.my.first.definition.MovieResourceDefinition;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movie")
public class MovieController implements MovieResourceDefinition {
    //@Autowired
    //private RestTemplate restTemplate;

    @GetMapping("hi")
    @HystrixCommand(fallbackMethod = "findByIdFailback")
    public User hi(@PathVariable Long id) {
        // http://localhost:7900/simple/
        // VIP virtual IP
        // HAProxy Heartbeat
        //return this.restTemplate.getForObject("http://microservice-provider-user/simple/" + id, User.class);
        return new User();
    }

    public User findByIdFailback(Long id) {
        User user = new User();
        user.setId(0L);
        return user;
    }

    @Override
    @RequestMapping(value = "/hi/{id}", method = RequestMethod.GET)
    public String hi(@PathVariable("id") String customerId){
        return "hi, NO." + customerId;
    }

    class User{
        private Long id;

        private String name;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
