package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

@Controller
public class AdminController {

    UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String allUsersAdmin(Model model) {
        model.addAttribute("users", userService.getListUsers());
        return "view/adminController/allUsers";
    }

    @GetMapping("/admin/add")
    public String addAdmin(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "view/adminController/addUser";
    }

    @PostMapping("/admin/saveInfo")
    public String saveUserAdmin(@ModelAttribute("user") User user) {
        userService.saveOrUpdate(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/updateInfo")
    public String updateUserAdmin(@RequestParam("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "view/adminController/addUser";
    }

    @PostMapping("/admin/delInfo")
    public String deleteUserAdmin(@RequestParam("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

}
