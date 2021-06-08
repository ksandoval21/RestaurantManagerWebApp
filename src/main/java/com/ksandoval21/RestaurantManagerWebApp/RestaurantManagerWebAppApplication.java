package com.ksandoval21.RestaurantManagerWebApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class RestaurantManagerWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantManagerWebAppApplication.class, args);
	}
	@Controller
	public class WelcomePage {
		@GetMapping("/")
		public String welcome(){
			return "welcome";
		}
	}

}
