package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.Set;

public interface UserService {
    void saveOrUpdate(User user);

    Set<User> getListUsers();

    Set<Role> getRoles();

    User getUserById(Long id);

    void delete(Long id);

    Role getRoleByName(String role);

}
