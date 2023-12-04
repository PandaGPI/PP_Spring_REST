package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Roles;
import ru.kata.spring.boot_security.demo.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveOrUpdate(User user, String[] roles) {
        User userFromDB = getUserById(user.getId());
        Set<Roles> rolesSet = Arrays.stream(roles)
                .map(this::getRoleByName)
                .collect(Collectors.toSet());
        if (userFromDB != null) {
            user.setRoles(rolesSet);
            entityManager.merge(user);
        } else {
            user.setRoles(rolesSet);
            entityManager.persist(user);
        }
    }

    @Override
    public Set<User> getListUsers() {
        TypedQuery<User> userList = entityManager.createQuery("FROM User", User.class);
        return userList.getResultList().stream().collect(Collectors.toSet());
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
    public Set<Roles> getListRoles() {
        TypedQuery<Roles> rolesList = entityManager.createQuery("FROM Roles", Roles.class);
        return rolesList.getResultList().stream().collect(Collectors.toSet());
    }

    @Override
    public Roles getRoleByName(String role) {
        Query query = entityManager.createQuery("FROM Roles WHERE role = :role");
        query.setParameter("role", role);
        return (Roles) query.getSingleResult();
    }
}
