package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDetailDAO;
import ru.kata.spring.boot_security.demo.models.User;

@Service
@Transactional
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserDetailDAO userDetailDAO;

    @Autowired
    public UserDetailServiceImpl(UserDetailDAO userDetailDAO) {
        this.userDetailDAO = userDetailDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDetailDAO.getUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
