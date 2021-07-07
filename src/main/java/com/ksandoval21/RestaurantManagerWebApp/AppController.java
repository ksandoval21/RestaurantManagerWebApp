package com.ksandoval21.RestaurantManagerWebApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

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
        List<Orders> orderList = (List<Orders>) orderRepo.findAll();
        model.addAttribute("ordersList", orderList);
        return "order_success";
    }
    @GetMapping("/management")
    public String listUser(Model model) {
        List<User> listUsers = (List<User>) userRepo.findAll();
        model.addAttribute("listUsers", listUsers);
        return "management";
    }
    @GetMapping("/employee")
    public String listUsers(Model model) {
        List<Orders> orderList = (List<Orders>) orderRepo.findAll();
        model.addAttribute("ordersList", orderList);
        return "employee";
    }
    @GetMapping("/edit/{id}")
    public ModelAndView editOrder(@PathVariable(name ="id")Long id) {
        ModelAndView mav =  new ModelAndView("edit_order");
        Optional<Orders> orders = orderRepo.findById(id);
        mav.addObject("orders",orders);
        return mav;
    }
    @PostMapping("/order_info")
    public String order_info(Orders newOrder){
        Optional<Orders> oldOrder = orderRepo.findById(newOrder.getId());
        if (oldOrder != null) {
            oldOrder.get().setDrinks(newOrder.getDrinks());
            oldOrder.get().setGuest(newOrder.getGuest());
            oldOrder.get().setKids(newOrder.getKids());
            oldOrder.get().setTableNumber(newOrder.getTableNumber());
            orderRepo.save(oldOrder.get());
        }
        return "redirect:/employee";
    }

    @GetMapping("/order_delete/{id}")
    public String order_delete(@PathVariable(name ="id")Long id, Orders orders){
        Optional<Orders> oldOrder = orderRepo.findById(orders.getId());
        oldOrder.ifPresent(value -> orderRepo.delete(oldOrder.get()));
        return "redirect:/employee";
    }
    @GetMapping("/view/{id}")
    public ModelAndView viewOrder(@PathVariable(name ="id")Long id) {
        ModelAndView mav =  new ModelAndView("specific-order-form");
        Optional<Orders> orders = orderRepo.findById(id);
        mav.addObject("orders",orders);
        return mav;
    }
    @PostMapping("/order/{id}")
    public String orderView(@PathVariable(name ="id")Long id, Orders orders, Model model,CustomUserDetails userDetails) {
        Optional<Orders> order = orderRepo.findById(orders.getId());
        order.ifPresent(value -> model.addAttribute("order", value));
        return "order_info";
    }
    @GetMapping("/priceslist")
    public String priceList(Model model) {
        List<Prices> priceList = (List<Prices>) priceRepo.findAll();
        model.addAttribute("priceList", priceList);
        return "pricelist";
    }
    @GetMapping("/pin/{id}")
    public ModelAndView editPrice(@PathVariable(name ="id")Long id) {
        ModelAndView mav =  new ModelAndView("prices-form");
        Optional<Prices> prices = priceRepo.findById(id);
        mav.addObject("prices", prices);
        return mav;
    }
    @PostMapping("/price_info")
    public String price_info(Prices newPrice){
        Optional<Prices> oldOrder = priceRepo.findById(newPrice.getId());
        if (oldOrder != null) {
            oldOrder.get().setGuest(newPrice.getGuest());
            oldOrder.get().setChild(newPrice.getChild());
            oldOrder.get().setDrink(newPrice.getDrink());
            priceRepo.save(oldOrder.get());
        }
        return "redirect:/priceslist";
    }

}
