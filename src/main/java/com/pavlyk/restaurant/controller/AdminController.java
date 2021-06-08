package com.pavlyk.restaurant.controller;

import com.pavlyk.restaurant.entity.Order;
import com.pavlyk.restaurant.service.api.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@Log4j
@AllArgsConstructor
public class AdminController {

    private final OrderService orderService;

    @GetMapping(value = {"/admin"})
    public ModelAndView admin(ModelMap modelMap, @RequestParam(defaultValue = "1") Integer pageId,
                              @RequestParam(defaultValue = "6") Integer pageSize,
            @RequestParam(required = false) String sortBy){
        log.info("Admin controller");
        List<Order> orderListPaginated;
        if("active".equals(sortBy)){
            orderListPaginated = orderService.getAllActiveOrders();
        }
        else{
            orderListPaginated = orderService.getAllOrderPaginated(pageId,pageSize);
        }
        int amountOfPages = orderService.getPageCount(pageId,pageSize);
        modelMap.put("pages", amountOfPages);
        modelMap.put("active", sortBy);
        modelMap.put("orders",orderListPaginated);
        modelMap.put("currentPage",pageId);
        return new ModelAndView("admin");
    }
}
