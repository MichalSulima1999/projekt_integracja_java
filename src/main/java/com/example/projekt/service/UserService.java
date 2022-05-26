package com.example.projekt.service;

import com.example.projekt.domain.Role;
import com.example.projekt.domain.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    User updateJWT(String username, String jwt);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    User getUserByJWT(String jwt);
    List<User> getUsers();
}
