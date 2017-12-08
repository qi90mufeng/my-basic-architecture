package com.my.first.controller;

import com.my.first.definition.MovieResourceDefinition;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
public class MovieController implements MovieResourceDefinition{

    @GetMapping("/movie/{id}")
    @HystrixCommand(fallbackMethod = "findByIdFailback")
    public User findById(@PathVariable Long id) {
        // http://localhost:7900/simple/
        // VIP virtual IP
        // HAProxy Heartbeat
        return new User();
    }

    @Override
    @RequestMapping(value = "/hi/{id}", method = RequestMethod.GET)
    public String hi(@PathVariable("id") String customerId){
        return "hi, NO." + customerId;
    }

    public User findByIdFailback(Long id) {
        User user = new User();
        user.setId(0L);
        return user;
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
