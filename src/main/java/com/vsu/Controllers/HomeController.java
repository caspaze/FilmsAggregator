package com.vsu.Controllers;

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

import java.io.IOException;
import java.util.List;
@Controller
@AllArgsConstructor
public class HomeController {
    private final FilmService filmService;
    @GetMapping("/")
    public String homePage(@AuthenticationPrincipal User user, Model model) throws IOException {

        List<FilmDTO> films = filmService.findLastFilms();
        model.addAttribute("film1",films.get(0));
        model.addAttribute("film2",films.get(1));
        model.addAttribute("film3",films.get(2));
        model.addAttribute("film4",films.get(3));
        return "index";
    }
    @GetMapping("/search")
    public String findByName(@RequestParam String name,
                             @RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "5") int size,
                             Model model){
        Page<FilmDTO> filmPage = filmService.findFilms(name,PageRequest.of(page,size));
        model.addAttribute("page",filmPage);
        model.addAttribute("name",name);
        return "search";
    }

}
