package com.ecommerce.customer.controller;

import com.ecommerce.library.dto.ProductDto;
import com.ecommerce.library.model.Category;
import com.ecommerce.library.service.CategoryService;
import com.ecommerce.library.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = {"/index", "/"}, method = RequestMethod.GET)
    public String index(Model model, Principal principal, HttpSession session) {
        if(principal != null){
            session.setAttribute("username",principal.getName());
        }
        else {
            session.removeAttribute("username");
        }
        model.addAttribute("title","Home");
        return "home";
    }

    @GetMapping("/home")
    public String home(Model model){
        List<Category> categories = categoryService.findAll();
        List<ProductDto> productDtos = productService.findAll();
        model.addAttribute("title", "Menu");
        model.addAttribute("categories",categories);
        model.addAttribute("products",productDtos);
        return "index";
    }
}
