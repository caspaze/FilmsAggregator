package com.vsu.Services;

import com.vsu.Models.User;
import com.vsu.Repository.RoleRepository;
import com.vsu.Repository.UserRepository;
import com.vsu.dto.UserConverter;
import com.vsu.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final RoleRepository roleRepository;


    public UserDTO findByUsername(String username){
        User user = userRepository.findByUsername(username);
        if(user!=null){
            return userConverter.userToUserDto(user);
        }
        return null;
    }


    public boolean addUser(User user){
        User userFromDB = userRepository.findByEmail(user.getEmail());
        if(userFromDB!=null){
            return false;
        }
        user.setEnabled(true);
        user.setRole(roleRepository.findRoleByName("user"));
        userRepository.save(user);
        return true;
    }

    public UserDTO findById(Long id){
        Optional<User> user = userRepository.findById(id);
        return userConverter.userToUserDto(user.get());
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if(user!=null){
            return user;
        }
        else{
            throw new UsernameNotFoundException(email);
        }

    }
}
