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
                .password(userDTO.getPassword())
                .role(userDTO.getRole())
                .build();

    }
    public UserDTO userToUserDto(User user){
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }
}
