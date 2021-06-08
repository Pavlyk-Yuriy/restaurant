package com.pavlyk.restaurant.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Log4j
public class LoginController {

    @GetMapping(value = {"/login"})
    public ModelAndView login(@RequestParam(value = "error",required = false) String error, Model model){
        log.info("Login controller");
        if(error!=null){
            model.addAttribute("errorMsg", "Password or email is wrong");
        }
        return new ModelAndView("login");
    }

}
