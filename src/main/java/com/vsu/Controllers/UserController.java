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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String userVotes(@PathVariable Long id,
                            @RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "10") int size, Model model){
        Page<Grade> grades = gradeService.findGrades(userService.findById(id), PageRequest.of(page,size));
        model.addAttribute("id", id);
        model.addAttribute("grades",grades);
        return "votes";
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
