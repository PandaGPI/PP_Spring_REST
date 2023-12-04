package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class UserDetailDAOImpl implements UserDetailDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUserByName(String name) {
        Query query = entityManager.createQuery("FROM User WHERE name = :name");
        query.setParameter("name", name);
        return (User) query.getSingleResult();
    }
}
