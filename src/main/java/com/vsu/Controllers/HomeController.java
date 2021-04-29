package com.vsu.Controllers;

import com.vsu.Models.Staff;
import com.vsu.Models.User;
import com.vsu.Services.FilmService;
import com.vsu.dto.FilmDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Controller
@AllArgsConstructor
public class HomeController {
    private final FilmService filmService;
    @GetMapping("/")
    public String homePage(@AuthenticationPrincipal User user, Model model){

        List<FilmDTO> films = filmService.findLastFilms();
        model.addAttribute("film0",films.get(0));
        model.addAttribute("film1",films.get(1));
        model.addAttribute("film2",films.get(2));
        model.addAttribute("film3",films.get(3));
        model.addAttribute("film4",films.get(4));
        model.addAttribute("film5",films.get(5));
        model.addAttribute("film6",films.get(6));
        model.addAttribute("film7",films.get(7));
        model.addAttribute("films",films);
        model.addAttribute("user",user);
        return "index";
    }
    @GetMapping("/search")
    public String findByName(@AuthenticationPrincipal User user,
                             @RequestParam String name,
                             @RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "5") int size,
                             Model model){
        Page<FilmDTO> filmPage = filmService.findFilms(name,PageRequest.of(page,size));
        Set<Staff> directors = new TreeSet<>(Comparator.comparing(Staff::getName));
        model.addAttribute("page",filmPage);
        model.addAttribute("name",name);
        model.addAttribute("user",user);
        return "search";
    }

}
