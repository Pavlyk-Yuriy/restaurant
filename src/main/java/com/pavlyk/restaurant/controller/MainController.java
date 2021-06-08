package com.pavlyk.restaurant.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Log4j
public class MainController {
    @GetMapping(value = {"/deny"})
    public ModelAndView deny(){
        log.info("deny controller");
        return new ModelAndView("deny");
    }
}
