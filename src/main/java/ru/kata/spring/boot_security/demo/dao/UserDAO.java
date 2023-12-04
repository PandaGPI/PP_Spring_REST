package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.models.Roles;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.Set;

public interface UserDAO {
    void saveOrUpdate(User user, String[] roles);

    Set<User> getListUsers();

    Set<Roles> getListRoles();

    User getUserById(Long id);

    void delete(Long id);

    Roles getRoleByName(String role);

}
