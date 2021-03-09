package com.vsu.Services;

import com.vsu.Models.DTO.UserConverter;
import com.vsu.Models.DTO.UserDTO;
import com.vsu.Models.Exceptions.ValidationException;
import com.vsu.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RepositoryUserService implements UserService{
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    @Override
    public UserDTO saveUser(UserDTO userDTO) throws ValidationException{

    }
    private void validateUserDto(UserDTO userDTO) throws ValidationException{
        if(userDTO==null){
            throw new ValidationException("Object user is null");
        }
        if(userDTO.getUsername()==null || userDTO.getUsername().isEmpty()){
            throw new ValidationException("Username is empty");
        }
    }

}
