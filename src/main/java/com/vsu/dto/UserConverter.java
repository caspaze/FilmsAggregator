package com.vsu.dto;

import com.vsu.Models.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public User userDtoToUser(UserDTO userDTO){
        return User.builder()
                .id(userDTO.getId())
                .email(userDTO.getEmail())
                .username(userDTO.getUsername())
                .role(userDTO.getRole())
                .grades(userDTO.getGrades())
                .reviews(userDTO.getReviews())
                .build();

    }
    public UserDTO userToUserDto(User user){
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .role(user.getRole())
                .grades(user.getGrades())
                .reviews(user.getReviews())
                .build();
    }
}
