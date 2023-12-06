package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String allUsersAdmin(Model model) {
        model.addAttribute("users", userService.getListUsers());
        model.addAttribute("user_authentication", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("listRoles", userService.getListRoles());
        return "/view/adminController/admin";
    }

    @PostMapping("/admin/saveInfo")
    public String saveUserAdmin(@ModelAttribute("user") User user, @RequestParam("listRoles") String[] roles) {
        userService.saveOrUpdate(user, roles);
        return "redirect:/admin";
    }

    @PostMapping("/admin/delInfo")
    public String deleteUserAdmin(@RequestParam("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

}
