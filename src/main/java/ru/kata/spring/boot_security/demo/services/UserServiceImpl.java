package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void saveOrUpdate(User user) {
        userDAO.saveOrUpdate(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<User> getListUsers() {
        return userDAO.getListUsers();
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Role> getRoles() {
        return userDAO.getRoles();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    @Override
    public void delete(Long id) {
        userDAO.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Role getRoleByName(String role) {
        return userDAO.getRoleByName(role);
    }
}
