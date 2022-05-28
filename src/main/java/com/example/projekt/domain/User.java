package com.example.projekt.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique=true)
    private String username;
    private String password;
    private String jwt;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.jwt = "";
        this.roles = new ArrayList<>();
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();
}

