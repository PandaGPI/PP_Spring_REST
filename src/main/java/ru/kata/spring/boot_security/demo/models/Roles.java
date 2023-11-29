package ru.kata.spring.boot_security.demo.models;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Roles implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "role")
    private String role;

    @Transient
    @ManyToMany(mappedBy = "roles")
    Set<User> users;

    public Roles(){};

    public Roles(Long id, String role) {
        this.id = id;
        this.role = role;
    }

    public Roles(String role, Set<User> users) {
        this.role = role;
        this.users = users;
    }

    public Long getId_role() {
        return id;
    }

    public void setId_role(Long id_role) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return getRole();
    }
}
