package com.pavlyk.restaurant.controller;

import com.pavlyk.restaurant.entity.User;
import com.pavlyk.restaurant.service.api.OrderService;
import com.pavlyk.restaurant.service.api.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.security.Principal;

@Controller
@AllArgsConstructor
@Log4j
public class ProfileController {
    private final UserService userService;
    private final OrderService orderService;

    @GetMapping(value = {"/profile"})
    public ModelAndView cabinet(ModelMap modelMap, Principal principal,
                                @RequestParam(defaultValue = "1") Integer pageId,
                                @RequestParam(defaultValue = "4") Integer pageSize){
        User user = userService.getUserByEmail(principal.getName());
        log.info("cabinet controller");
        modelMap.put("user", user);
        modelMap.put("photo", 1 + (int)(Math.random() * ((8 - 1) + 1)));
        modelMap.put("orders", orderService.getOrdersPaginated(pageId,pageSize,user.getId()));
        modelMap.put("pages", orderService.getTotalPagesCount(pageId,pageSize, user.getId()));
        modelMap.put("currentPage", pageId);
        return new ModelAndView("profile");
    }

    @GetMapping(value = {"/editProfile"})
    public ModelAndView editProfile(ModelMap modelMap, Principal principal){
        User user = userService.getUserByEmail(principal.getName());
        log.info("cabinet controller");
        modelMap.put("user", user);
        modelMap.addAttribute("userModel", new User());
        modelMap.put("photo", 1 + (int)(Math.random() * ((8 - 1) + 1)));
        return new ModelAndView("editProfile");
    }

    @PostMapping(value = {"/saveEditedProfile"})
    public String saveEditedProfile(@ModelAttribute("userModel") User userModel,
                                          ModelMap modelMap, Principal principal){
        log.info("cabinet controller");
        User user = userService.getUserByEmail(principal.getName());
        user.setEmail(userModel.getEmail());
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setEmail(userModel.getEmail());
        user.setAddress(userModel.getAddress());
        user.setPhoneNumber(userModel.getPhoneNumber());
        userService.updateUser(user);
        modelMap.put("user", user);
        modelMap.put("photo", 1 + (int)(Math.random() * ((8 - 1) + 1)));
        return "redirect:/profile";
    }
}
