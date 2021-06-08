package com.pavlyk.restaurant.controller;

import com.pavlyk.restaurant.entity.User;
import com.pavlyk.restaurant.service.api.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


import javax.validation.Valid;

@Controller
@AllArgsConstructor
@Log4j
public class RegistrationController {

    private final UserService userService;

    @GetMapping(value = {"/registration"})
    public ModelAndView registration(){
        log.info("Registration controller");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userModel", new User());
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userModel") @Valid User userModel,
            BindingResult result, ModelMap model){
        if(!userModel.getPassword().equals(userModel.getPasswordConfirm())){
            model.addAttribute("error", "Confirm password is not equals password");
            return "registration";
        }
        else if (result.hasErrors()) {
            model.addAttribute("error", "Email address is wrong");
            return "registration";
        }
        if (!userService.saveUser(userModel)){
            model.addAttribute("usernameError", "A user with this name already exists");
            return "registration";
        }
        return "login";
    }
}
