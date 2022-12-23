package com.ecommerce.admin.controller;

import com.ecommerce.library.dto.AdminDto;
import com.ecommerce.library.model.Admin;
import com.ecommerce.library.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("title","Login");
        return "login";
    }

    @GetMapping("/index")
    public String home(Model model){
        model.addAttribute("title","Home Page");
        return "index";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("title","Register");
        model.addAttribute("adminDto", new AdminDto());
        return "register";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword(Model model) {
        model.addAttribute("title","Forgot Password");
        return "forgot-password";
    }

    @PostMapping("/register-new")
    public String addNewAdmin(@Valid @ModelAttribute("adminDto")AdminDto adminDto,
                              BindingResult result,
                              Model model) {
        try {
            if(result.hasErrors()){
                model.addAttribute("adminDto", adminDto);
                result.toString();
                return "register";
            }
            String username = adminDto.getUsername();
            Admin admin = adminService.findByUsername(username);
            if(admin != null){
                model.addAttribute("adminDto", adminDto);
                model.addAttribute("error","Your email has been already registered!");
                System.out.println("admin is not null");
                return "register";
            }
            if(adminDto.getPassword().equals(adminDto.getRepeatPassword())){
                adminDto.setPassword(passwordEncoder.encode(adminDto.getPassword()));
                adminService.save(adminDto);
                System.out.println("success");
                model.addAttribute("successRegister","Registered Successfully!");
                model.addAttribute("adminDto", adminDto);
            }else {
                model.addAttribute("adminDto", adminDto);
                model.addAttribute("passwordError","Password is not same!");
                System.out.println("password is not same");
                return "register";
            }
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("serverError","server is error, please try again later!");
        }
        return "register";
    }
}
