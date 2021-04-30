package com.vsu.Controllers;

import com.vsu.Models.User;
import com.vsu.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){
        boolean isActivated = userService.activateUser(code);
        if(isActivated){
            model.addAttribute("message","User successfully activated");
        }
        else{
            model.addAttribute("message","Activation code is not found");
        }
        return "login";
    }
}
