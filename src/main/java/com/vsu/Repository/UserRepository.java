package com.vsu.Repository;

import com.vsu.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String login);
    User findByEmail(String email);
}
