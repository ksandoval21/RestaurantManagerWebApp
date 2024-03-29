package com.ksandoval21.RestaurantManagerWebApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
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

    //user sign up
    @GetMapping("/register")
    public String signUpForm(Model model) {
        model.addAttribute("user", new User());
        return "signup_form";
    }
    // Encryps password after account created
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

    //User create order
    @GetMapping("/order")
    public String addOrder(Model model) {
        model.addAttribute("orders", new Orders());
        return "customer";
    }

    @PostMapping("/order-success")
    public String processOrder(Orders orders, @AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        orderRepo.save(orders);
        List<Orders> orderList = (List<Orders>) orderRepo.findAll();
        Optional<Prices> prices = priceRepo.findById(1L);
        model.addAttribute("ordersList", orderList);
        model.addAttribute("prices", prices.get());
        return "order_success";
    }

    //list of users
    @GetMapping("/management")
    public String listUser(Model model) {
        List<User> listUsers = (List<User>) userRepo.findAll();
        model.addAttribute("listUsers", listUsers);
        return "management";
    }

    // list of orders
    @GetMapping("/employee")
    public String listUsers(Model model) {
        List<Orders> orderList = (List<Orders>) orderRepo.findAll();
        model.addAttribute("ordersList", orderList);
        return "employee";
    }

    //edit orders
    @GetMapping("/edit/{id}")
    public ModelAndView editOrder(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_order");
        Optional<Orders> orders = orderRepo.findById(id);
        mav.addObject("orders", orders);
        return mav;
    }

    @PostMapping("/order_info")
    public String order_info(Orders newOrder) {
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

    @GetMapping("/update/{id}")
    public ModelAndView updateOrder(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("update_order");
        Optional<Orders> orders = orderRepo.findById(id);
        mav.addObject("orders", orders);
        return mav;
    }

    @PostMapping("/order_update")
    public String update_info(Orders newOrder) {
        Optional<Orders> oldOrder = orderRepo.findById(newOrder.getId());
        if (oldOrder != null) {
            oldOrder.get().setDrinks(newOrder.getDrinks());
            oldOrder.get().setGuest(newOrder.getGuest());
            oldOrder.get().setKids(newOrder.getKids());
            oldOrder.get().setTableNumber(newOrder.getTableNumber());
            orderRepo.save(oldOrder.get());
        }
        return "redirect:/find-order";
    }

    // delete order
    @GetMapping("/order_delete/{id}")
    public String order_delete(@PathVariable(name = "id") Long id, Orders orders) {
        Optional<Orders> oldOrder = orderRepo.findById(orders.getId());
        oldOrder.ifPresent(value -> orderRepo.delete(oldOrder.get()));
        return "redirect:/employee";
    }

    @GetMapping("/pay/{id}")
    public String payOrder(@PathVariable(name = "id") Long id, Orders orders) {
        Optional<Orders> oldOrder = orderRepo.findById(orders.getId());
        oldOrder.ifPresent(value -> orderRepo.delete(oldOrder.get()));
        return "thank-you";
    }

    //view specific
    @GetMapping("/find-order")
    public String viewSpecificOrder() {
        return "specific-order-form";
    }

    @GetMapping("/show-order")
    public String orderView(@RequestParam int tableNumber, Model model, CustomUserDetails userDetails) {
        Optional<Orders> order = orderRepo.findByTableNumber(tableNumber);
        Optional<Prices> prices = priceRepo.findById(1L);
        model.addAttribute("order", order.get());
        model.addAttribute("prices", prices.get());
        return "order_info";
    }

    @GetMapping("/menu")
    public String prices(Model model) {
        Optional<Prices> prices = priceRepo.findById(1L);
        model.addAttribute("prices", prices.get());
        return "menu";
    }

    //price list
    @GetMapping("/priceslist")
    public String priceList(Model model) {
        List<Prices> priceList = (List<Prices>) priceRepo.findAll();
        model.addAttribute("priceList", priceList);
        return "pricelist";
    }
    // Pin to edit prices
    @GetMapping("/pin/{id}")
    public ModelAndView editPrice(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("prices-form");
        Optional<Prices> prices = priceRepo.findById(id);
        mav.addObject("prices", prices);
        return mav;
    }
    // edit prices
    @PostMapping("/price_info")
    public String price_info(Prices newPrice) {
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
