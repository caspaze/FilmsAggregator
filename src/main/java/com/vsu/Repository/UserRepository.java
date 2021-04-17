package com.vsu.Repository;

import com.vsu.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String login);
    User findByEmail(String email);
    @Transactional
    @Modifying
    @Query("update User u set u=:user")
    void updateUser(@Param("user") User updatedUser);
}
