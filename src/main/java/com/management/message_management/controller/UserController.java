package com.management.message_management.controller;

import com.management.message_management.model.User;
import com.management.message_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String getAllUsers(Model model){
    model.addAttribute("user",userService.getAllUsers());
    return "home";
    }
}
