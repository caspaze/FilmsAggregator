package com.vsu.Services;

import com.vsu.dto.UserConverter;
import com.vsu.dto.UserDTO;
import com.vsu.Models.Exceptions.ValidationException;
import com.vsu.Models.User;
import com.vsu.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RepositoryUserService{
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public UserDTO saveUser(UserDTO userDTO) throws ValidationException{
        validateUserDto(userDTO);
        User savedUser = userRepository.save(userConverter.userDtoToUser(userDTO));
        return userConverter.userToUserDto(savedUser);
    }


    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public UserDTO findByEmail(String email){
        User user = userRepository.findByUsername(email);
        if(user!=null){
            return userConverter.userToUserDto(user);
        }
        return null;
    }
    public List<UserDTO> findAll(){
        return userRepository.findAll().stream().map(userConverter::userToUserDto).collect(Collectors.toList());
    }

    public UserDTO findById(Long id){
        Optional<User> user = userRepository.findById(id);
        return userConverter.userToUserDto(user.get());
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
