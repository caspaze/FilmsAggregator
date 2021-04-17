package com.vsu.Controllers;

import com.vsu.Models.Grade;
import com.vsu.Models.User;
import com.vsu.Services.GradeService;
import com.vsu.dto.UserDTO;
import com.vsu.Services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.time.LocalDate;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
@Log
public class UserController {
    private final UserService userService;
    private final GradeService gradeService;
    @GetMapping("/{id}")
    public String userProfile(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("grades", user.getGrades());
        model.addAttribute("reviews", user.getReviews());
        return "user";
    }
    @GetMapping("/{id}/votes")
    public String userVotes(@AuthenticationPrincipal User user,
                            @PathVariable Long id,
                            @RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "10") int size, Model model){
        Page<Grade> grades = gradeService.findGrades(userService.findById(id), PageRequest.of(page,size));
        model.addAttribute("id", id);
        model.addAttribute("grades",grades);
        model.addAttribute("user",user);
        return "votes";
    }

    @GetMapping("/settings")
    public String userSettings(@AuthenticationPrincipal User user,Model model){
        model.addAttribute("user",user);
        return "settings";
    }
    @PostMapping("/settings")
    public String saveUserSettings(@AuthenticationPrincipal User user,
                                   String email,
                                   String username,
                                   String country,
                                   @DateTimeFormat(pattern = "yyyy-MM-dd")
                                   LocalDate birthdate,
                                   String vkLink,
                                   String googleLink,
                                   String twitterLink){
        userService.updateUser(user,email,username,country,birthdate,vkLink,googleLink,twitterLink);
        return "redirect:/user/" + user.getId();
    }

}
