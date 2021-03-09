package com.vsu.Services;

import com.vsu.Models.DTO.UserDTO;
import com.vsu.Models.Exceptions.ValidationException;

import java.util.List;

public interface UserService {
    
    UserDTO saveUser(UserDTO usersDto) throws ValidationException;

    void deleteUser(Integer userId);

    UserDTO findByLogin(String login);

    List<UserDTO> findAll();
}
