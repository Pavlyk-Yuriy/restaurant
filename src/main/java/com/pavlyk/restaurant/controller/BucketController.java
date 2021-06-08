package com.pavlyk.restaurant.controller;

import com.pavlyk.restaurant.entity.Bucket;
import com.pavlyk.restaurant.entity.BucketItem;
import com.pavlyk.restaurant.entity.User;
import com.pavlyk.restaurant.service.api.BucketItemService;
import com.pavlyk.restaurant.service.api.BucketService;
import com.pavlyk.restaurant.service.api.DishService;
import com.pavlyk.restaurant.service.api.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Controller
@AllArgsConstructor
@Log4j
public class BucketController {
    private final UserService userService;
    private final BucketService bucketService;
    private final DishService dishService;
    private final BucketItemService bucketItemService;

    @GetMapping("/bucket")
    public ModelAndView bucket(Model modelMap, Principal principal) {
        System.out.println(principal.getName());
        User user = userService.getUserByEmail(principal.getName());
        Optional<Bucket> bucket = bucketService.getBucketByUserId(user.getId());
        bucket.ifPresent(value -> modelMap.addAttribute("bucket", value));
        return new ModelAndView("bucket");
    }

    @GetMapping("/addToBucket")
    public RedirectView addToBucket(@RequestParam Long dishId, RedirectAttributes redirectAttributes,
                                    Principal principal, @RequestParam Long pageId, @RequestParam String sortBy,
                                    @RequestParam String category) {
        log.info("add to bucket");
        User user = userService.getUserByEmail(principal.getName());
        if (user.getBucket() == null) {
            createBucket(user);
        }
        Bucket bucket = user.getBucket();
        Optional<BucketItem> bucketItem = Optional.ofNullable(bucketItemService
                .getBucketItemByDishIdAndAndBucketId(dishId, bucket.getId()));
        if (bucketItem.isPresent()) {
            increaseDishCount(bucketItem.get());
            redirectAttributes.addFlashAttribute("message", "Amount of dishes successfully increased");
        } else {
            BucketItem newBucketItem = BucketItem.builder()
                    .dish(dishService.getDishById(dishId).get())
                    .quantity(1)
                    .bucket(bucket)
                    .build();
            bucketItemService.save(newBucketItem);
            redirectAttributes.addFlashAttribute("message", "Dish successfully added to cart");
            redirectAttributes.addFlashAttribute("pageId", pageId);
            log.info("dish added to bucket");
        }
        return new RedirectView("index?pageId=" + pageId + "&sortBy=" + sortBy + "&category=" + category);
    }

    private void createBucket(User user) {
        log.info("User don`t have bucket. Creating bucket...");
        Bucket bucket = Bucket.builder()
                .createdDate(LocalDateTime.now())
                .totalPrice(0)
                .user(user)
                .build();
        user.setBucket(bucket);
        userService.updateUser(user);
    }

    @GetMapping("/removeFromBucket")
    public RedirectView removeFromBucket(@RequestParam Long dishId, Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        Bucket currentBucket = user.getBucket();
        Set<BucketItem> bucketItemList = currentBucket.getBucketItems();
        BucketItem bucketItem = bucketItemService
                .getBucketItemByDishIdAndAndBucketId(dishId, currentBucket.getId());
        bucketItemList.remove(bucketItem);
        currentBucket.setBucketItems(bucketItemList);
        bucketService.saveBucket(currentBucket);
        bucketItemService.delete(bucketItem);
        log.info("remove from bucket");
        return new RedirectView("bucket");
    }

    @GetMapping("/deleteAllDishesFromBucket")
    public String deleteAllDishesFromBucket(@RequestParam Long bucketId) {
        bucketItemService.deleteAllBucketItemByBucketId(bucketId);
        log.info("bucket items is deleted");
        return "redirect:/bucket";
    }

    @GetMapping("/decreaseDish")
    public RedirectView decreaseDish(@RequestParam Long dishId, RedirectAttributes redirectAttributes,
                                     Principal principal) {
        Bucket bucket = userService.getUserByEmail(principal.getName()).getBucket();
        BucketItem currentBucketItem = bucketItemService
                .getBucketItemByDishIdAndAndBucketId(dishId, bucket.getId());
        if (decreaseDishCount(currentBucketItem)) {
            log.info("count of dish decreased successfully");
            redirectAttributes.addFlashAttribute("decreaseMsg", "Amount of dishes successfully decreased.");
        } else {
            redirectAttributes.addFlashAttribute("decreaseMsgError", "Amount of dishes equals to 1. " +
                    "Please, remove the dish from the cart.");
        }
        return new RedirectView("bucket");
    }

    @GetMapping("/increaseDish")
    public RedirectView increaseDish(@RequestParam Long dishId, RedirectAttributes redirectAttributes,
                                     Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        Optional<Bucket> bucket = bucketService.getBucketByUserId(user.getId());
        BucketItem currentBucketItem = bucketItemService
                .getBucketItemByDishIdAndAndBucketId(dishId, bucket.get().getId());
        increaseDishCount(currentBucketItem);
        redirectAttributes.addFlashAttribute("message", "Amount of dishes successfully increased.");
        log.info("count of dish increased successfully");
        return new RedirectView("bucket");
    }

    private void increaseDishCount(BucketItem currentBucketItem) {
        currentBucketItem.setQuantity(currentBucketItem.getQuantity() + 1);
        bucketItemService.save(currentBucketItem);
        log.info("amount of dishes increased");
    }

    private boolean decreaseDishCount(BucketItem currentBucketItem) {
        if (currentBucketItem.getQuantity() == 1) {
            log.info("amount of dishes equal 1. We can`t decrease amount of dishes");
            return false;
        } else {
            currentBucketItem.setQuantity(currentBucketItem.getQuantity() - 1);
            bucketItemService.save(currentBucketItem);
            log.info("amount of dishes decreased");
            return true;
        }
    }
}
