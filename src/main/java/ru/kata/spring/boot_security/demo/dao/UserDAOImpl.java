package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Roles;
import ru.kata.spring.boot_security.demo.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveOrUpdate(User user) {
        User userFromDB = getUserById(user.getId());
        if (userFromDB != null) {
            user.setRoles(userFromDB.getRoles());
            entityManager.merge(user);
        } else {
            user.setRoles(Collections.singleton(new Roles(1L, "ROLE_USER")));
            entityManager.merge(user);
        }
    }

    @Override
    public List<User> getListUsers() {
        TypedQuery<User> userList = entityManager.createQuery("FROM User", User.class);
        return userList.getResultList();
    }

    @Override
    public User getUserById(Long id) {
        if (id == null) {
            return null;
        } else {
            return entityManager.find(User.class, id);
        }
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    public User getUserByName(String name) {
        Query query = entityManager.createQuery("FROM User WHERE name = :name");
        query.setParameter("name", name);
        return (User) query.getSingleResult();
    }

    @Override
    public Set<Roles> getListRoles() {
        TypedQuery<Roles> rolesList = entityManager.createQuery("FROM Roles", Roles.class);
        return rolesList.getResultStream().collect(Collectors.toSet());
    }


}
