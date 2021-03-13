package com.vsu.Controllers;

import com.vsu.Models.DTO.UserDTO;
import com.vsu.Models.Exceptions.ValidationException;
import com.vsu.Services.RepositoryUserService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
@Log
public class UserController {
    private final RepositoryUserService userService;
    @PostMapping("/save")
    public UserDTO saveUser(@RequestBody UserDTO userDTO) throws ValidationException{
        log.info("Handling save user: " + userDTO);
        return userService.saveUser(userDTO);
    }
    @GetMapping("/{id}")
    public String getUser(@PathVariable Integer id, Model model) {
        log.info("Handling find all users request");
        UserDTO userDTO = userService.findById(id);
        model.addAttribute("id", userDTO.getId());
        return "user";
    }
    @GetMapping("/findByEmail")
    public UserDTO findByEmail(@RequestParam String email){
        log.info("Handling find by login request: " + email);
        return userService.findByEmail(email);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id){
        log.info("Handling delete user request: " + id);
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/test")
    public String test(){
        return "home";
    }

}
