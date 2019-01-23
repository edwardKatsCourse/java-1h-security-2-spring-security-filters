package com.telran.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/*
@Bean
public MyController myController() {
    return new MyController();
}

*/

@RestController
public class MyController {

    @GetMapping("/secured")
    public String secured(Principal principal) {

        return principal.getName() + " has reached security section!";
    }


    //registration
    //login
    //info
    //about
    @GetMapping("/non-secured")
    public String nonSecured() {
        return "we have reached non secured section!";
    }
}
