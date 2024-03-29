package com.vsu.Repository;

import com.vsu.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String login);
    User findByEmail(String email);

    User findByActivationCode(String code);

}
