package com.vsu.Controllers;

import com.vsu.Models.User;
import com.vsu.Repository.RoleRepository;
import com.vsu.Services.UserService;
import com.vsu.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
@AllArgsConstructor
public class RegistrationController {
    private final UserService userService;
    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }
    @PostMapping("/registration")
    public String registerUser(User user,String confirmPassword, Map<String,Object> model){
        if(!userService.addUser(user)){
            model.put("message","Пользователь с таким Email уже существует");
            return "registration";
        }
        if(!user.getPassword().equals(confirmPassword)){
            model.put("message","Пароли не совпадают");
            return "registration";
        }
        return "redirect:/login";
    }
}
