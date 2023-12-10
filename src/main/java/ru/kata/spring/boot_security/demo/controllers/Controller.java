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

//    @GetMapping("/admin")
//    public String allUsersAdmin(Model model) {
//        model.addAttribute("users", userService.getListUsers());
//        model.addAttribute("user_authentication", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        model.addAttribute("listRoles", userService.getListRoles());
//        return "/view/adminController/admin";
//    }

//    @PostMapping("/admin/saveInfo")
//    public String saveUserAdmin(@ModelAttribute("user") User user) {
//        userService.saveOrUpdate(user);
//        return "redirect:/admin";
//    }

//    @PostMapping("/admin/delInfo")
//    public String deleteUserAdmin(@RequestParam("id") Long id) {
//        userService.delete(id);
//        return "redirect:/admin";
//    }

    @GetMapping("/user")
    public String user() {
        return "/view/controller/user";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        return "/view/controller/admin";
    }
}
