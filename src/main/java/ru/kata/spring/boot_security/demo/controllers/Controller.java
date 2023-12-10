package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.services.UserService;

@org.springframework.stereotype.Controller
public class Controller {

    private final UserService userService;

    @Autowired
    public Controller(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String user() {
        return "/view/controller/user";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        return "/view/controller/admin";
    }
}
