package com.management.message_management.controller;

import com.management.message_management.model.User;
import com.management.message_management.service.KafkaProducerService;
import com.management.message_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @GetMapping("/home")
    public String getAllUsers(Model model){
    model.addAttribute("user",userService.getAllUsers());
    return "home";
    }
    @PostMapping("/save")
    public String saveUser(@RequestParam("empNo") int empNo,
                           @RequestParam("empName") String empName,
                           @RequestParam("designation") String designation,
                           @RequestParam("email") String email){

        System.out.println(empNo);
        System.out.println(empName);
        System.out.println(designation);
        System.out.println(email);

        User user = new User();
        user.setEmpNo(empNo);
        user.setEmpName(empName);
        user.setDesignation(designation);
        user.setEmail(email);
        userService.saveUser(user);


        kafkaProducerService.sendMessage(email);
        
        return "redirect:/home";
    }
}
