package com.my.first.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movice")
public class MovieController {

    @GetMapping("/movie/{id}")
    public User findById(@PathVariable Long id) {
        // http://localhost:7900/simple/
        // VIP virtual IP
        // HAProxy Heartbeat
        return new User();
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
