package com.Startup.Spring.Boot;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Sample {
    @GetMapping("/hello")
    public String sayHello() {
        return "Hi kuhu";
    }

}
