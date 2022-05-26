package com.example.projekt.repo;

import com.example.projekt.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByjwt(String jwt);
}
