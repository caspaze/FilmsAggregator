package com.vsu.Controllers;

import com.vsu.Models.User;
import com.vsu.dto.UserDTO;
import com.vsu.Services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public String userProfile(@AuthenticationPrincipal User user,@PathVariable Long id, Model model) {
        user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("grades",user.getGrades());
        model.addAttribute("reviews",user.getReviews());
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
