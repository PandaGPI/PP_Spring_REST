package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.Set;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    private final UserService userService;

    @Autowired
    public RestController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/user")
    public User showUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @GetMapping("/users")
    public Set<User> showUsers() {
        return userService.getListUsers();
    }

    @GetMapping("/roles")
    public Set<Role> showRoles() {
        return userService.getRoles();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/delUser/{id}")
    public Set<User> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return showUsers();
    }

    @PostMapping("/saveOrUpdate")
    public Set<User> saveOrUpdate(@RequestBody User user) {
        userService.saveOrUpdate(user);
        return showUsers();
    }

}
