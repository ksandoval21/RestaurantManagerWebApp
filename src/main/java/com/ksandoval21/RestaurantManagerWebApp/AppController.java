package com.ksandoval21.RestaurantManagerWebApp;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AppController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private PricesRepository priceRepo;

    @GetMapping("/register")
    public String signUpForm(Model model) {
        model.addAttribute("user", new User());
        return "signup_form";
    }
    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepo.save(user);
        return "register_success";
    }
    @GetMapping("/users")
    public String loggedIn(Model model) {
        return "if_page";
    }

    @GetMapping("/order")
    public String addOrder(Model model) {
        model.addAttribute("orders", new Orders());
        return "customer";
    }
    @PostMapping("/order-success")
    public String processOrder(Orders orders, @AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        orderRepo.save(orders);
        List<Orders> orderList = orderRepo.findAll();
        model.addAttribute("ordersList", orderList);
        return "order_success";
    }
    @GetMapping("/management")
    public String listUser(Model model) {
        List<User> listUsers = userRepo.findAll();
        model.addAttribute("listUsers", listUsers);
        return "management";
    }
    @GetMapping("/employee")
    public String listUsers(Model model) {
        List<Orders> orderList = orderRepo.findAll();
        model.addAttribute("ordersList", orderList);
        return "employee";
    }
}
