package com.pavlyk.restaurant.controller;

import com.pavlyk.restaurant.entity.Dish;
import com.pavlyk.restaurant.service.api.CategoryService;
import com.pavlyk.restaurant.service.api.DishService;
import com.pavlyk.restaurant.service.api.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@Log4j
public class MenuController {
    private final DishService dishService;
    private final CategoryService categoryService;
    private final UserService userService;


    @GetMapping(value = {"/index", "/"})
    public ModelAndView index(ModelMap modelMap, @RequestParam(defaultValue = "1") Integer pageId,
                              @RequestParam(defaultValue = "6") Integer pageSize,
                              @RequestParam(defaultValue = "id") String sortBy,
                              @RequestParam(required = false) String category, Principal principal) {
        log.info("Index controller");
        List<Dish> dishList;
        Integer pageCount;
        if (principal == null || !"ADMIN".equals(userService.getUserByEmail(principal.getName()).getRole())) {
            dishList = dishService.getAllDishesPaginated(pageId, pageSize, sortBy, category, false);
            pageCount = dishService.getPageCount(pageId, pageSize, category, false);
        } else {
            dishList = dishService.getAllDishesPaginated(pageId, pageSize, sortBy, category, true);
            pageCount = dishService.getPageCount(pageId, pageSize, category, true);
        }
        modelMap.put("dishes", dishList);
        modelMap.put("pages", pageCount);
        modelMap.put("currentPage", pageId);
        modelMap.put("sort", sortBy);
        modelMap.put("allCategoryList", categoryService.getAllCategory());
        modelMap.put("categoryList", dishService.getAllDishes().stream()
                .map(Dish::getCategory)
                .distinct()
                .collect(Collectors.toList()));
        modelMap.put("category", category);
        return new ModelAndView("index");
    }

    @GetMapping("/disabledDish")
    public String removeDish(@RequestParam Long dishId, @RequestParam(defaultValue = "1") Integer pageId,
                             @RequestParam(defaultValue = "id") String sortBy,
                             @RequestParam(required = false) String category) {
        Optional<Dish> dish = dishService.getDishById(dishId);
        dish.get().setDeleted(true);
        dishService.save(dish.get());
        return "redirect:/index?pageId=" + pageId + "&sortBy=" + sortBy + "&category=" + category;
    }

    @GetMapping("/enabledDish")
    public String activeDish(@RequestParam Long dishId, @RequestParam(defaultValue = "1") Integer pageId,
                             @RequestParam(defaultValue = "id") String sortBy,
                             @RequestParam(required = false) String category) {
        Optional<Dish> dish = dishService.getDishById(dishId);
        dish.get().setDeleted(false);
        dishService.save(dish.get());
        return "redirect:/index?pageId=" + pageId + "&sortBy=" + sortBy + "&category=" + category;
    }

}
