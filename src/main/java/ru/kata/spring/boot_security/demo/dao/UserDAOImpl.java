package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
            entityManager.merge(user);
        } else {
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
    public Set<Role> getRoles() {
        TypedQuery<Role> rolesList = entityManager.createQuery("FROM Role", Role.class);
        return rolesList.getResultList().stream().collect(Collectors.toSet());
    }

    @Override
    public Role getRoleByName(String role) {
        Query query = entityManager.createQuery("FROM Role WHERE role = :role");
        query.setParameter("role", role);
        return (Role) query.getSingleResult();
    }
}
