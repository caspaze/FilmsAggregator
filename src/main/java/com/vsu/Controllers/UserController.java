package com.vsu.Controllers;

import com.vsu.dto.UserDTO;
import com.vsu.Models.Exceptions.ValidationException;
import com.vsu.Services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
@Log
public class UserController {
    private final UserService userService;
    @GetMapping("/{id}")
    public String getUser(@PathVariable Long id, Model model) {
        log.info("Handling find all users request");
        UserDTO userDTO = userService.findById(id);
        model.addAttribute("user", userDTO);
        model.addAttribute("grades",userDTO.getGrades());
        model.addAttribute("reviews",userDTO.getReviews());
        return "user";
    }
    @GetMapping("/findByEmail")
    public String findByEmail(@RequestParam String username, Model model){
        log.info("Handling find by login request: " + username);
        UserDTO userDTO = userService.findByUsername(username);
        model.addAttribute("user", userDTO);
        model.addAttribute("grades",userDTO.getGrades());
        model.addAttribute("reviews",userDTO.getReviews());
        return "user";
    }


}
