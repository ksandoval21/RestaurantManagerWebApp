package com.ksandoval21.RestaurantManagerWebApp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomePage {
    @RequestMapping("/")
    public String welcome(){
        return "Welcome to Restaurant Manager";
    }
}
