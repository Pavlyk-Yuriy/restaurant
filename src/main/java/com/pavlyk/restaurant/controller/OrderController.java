package com.pavlyk.restaurant.controller;

import com.pavlyk.restaurant.entity.Bucket;
import com.pavlyk.restaurant.entity.Order;
import com.pavlyk.restaurant.entity.OrderStatus;
import com.pavlyk.restaurant.entity.User;
import com.pavlyk.restaurant.service.api.*;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.Optional;

@Controller
@AllArgsConstructor
@Log4j
public class OrderController {
    private final BucketService bucketService;
    private final OrderService orderService;
    private final UserService userService;
    private final OrderStatusService orderStatusService;
    private final BucketItemService bucketItemService;

    @PostMapping("/createOrder")
    public RedirectView createOrder(Principal principal, @RequestParam("address") String address,
                              RedirectAttributes redirectAttributes){
        User user = userService.getUserByEmail(principal.getName());
        Optional<Bucket> bucket = bucketService.getBucketByUserId(user.getId());
        log.info("Create order");
        Order order = orderService.mapBucketToOrder(bucket.get());
        order.setUser(user);
        order.setDeliveryAddress(address);
        orderService.save(order);
        bucketItemService.deleteAllBucketItemByBucketId(bucket.get().getId());
        redirectAttributes.addFlashAttribute("orderMsg","The order was successful.");
        return new RedirectView("index");
    }

    @GetMapping(value = {"/getUserOrderInfo"})
    public RedirectView getUserOrderInfo(@RequestParam Long orderId,@RequestParam Long pageId,
                                         @RequestParam(required = false) String sortBy,
                                         RedirectAttributes redirectAttributes){
        log.info("GetUserOrderInfo controller");
        Optional<Order> order = orderService.getOrderById(orderId);
        redirectAttributes.addFlashAttribute("currentOrder",order.get());
        return new RedirectView("admin?pageId="+pageId+"&sortBy="+sortBy);
    }

    @GetMapping("/editOrder")
    public ModelAndView editOrder(@RequestParam Long orderId, ModelMap modelMap){
        log.info("edit order controller");
        Optional<Order> order = orderService.getOrderById(orderId);
        modelMap.addAttribute("order", order.get());
        modelMap.put("orderStatus",orderStatusService.getAllOrderStatus());
        return new ModelAndView("editOrder", "command", order.get());
    }

    @PostMapping("/saveEditedOrder")
    public String saveEditedOrder(@ModelAttribute("orderModel") Order order,
                                  @RequestParam Long id){
        log.info("save order controller");
        OrderStatus orderStatus = orderStatusService.getOrderStatusByName(order.getOrderStatus().getName());
        Optional<Order> currentOrder = orderService.getOrderById(id);
        currentOrder.get().setOrderStatus(orderStatus);
        currentOrder.get().setPaymentStatus(order.isPaymentStatus());
        orderService.save(currentOrder.get());
        return "redirect:/admin";
    }

}
