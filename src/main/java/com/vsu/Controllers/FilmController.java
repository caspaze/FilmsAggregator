package com.vsu.Controllers;

import com.vsu.Models.Film;
import com.vsu.Models.FilmStaff;
import com.vsu.Models.Staff;
import com.vsu.Models.User;
import com.vsu.dto.FilmDTO;
import com.vsu.Services.FilmService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/film")
@AllArgsConstructor
public class FilmController {
    private final FilmService filmService;
    @GetMapping("/{id}")
    public String getFilm(@AuthenticationPrincipal User user, @PathVariable Long id, Model model) throws IOException {
        FilmDTO filmDTO = filmService.findById(id);
        Set<Staff> directors = new TreeSet<>(Comparator.comparing(Staff::getName));
        Set<Staff> producers = new TreeSet<>(Comparator.comparing(Staff::getName));
        Set<Staff> actors = new TreeSet<>(Comparator.comparing(Staff::getName));
        Set<Staff> screenwriters = new TreeSet<>(Comparator.comparing(Staff::getName));
        Set<Staff> operators = new TreeSet<>(Comparator.comparing(Staff::getName));
        Set<Staff> composers = new TreeSet<>(Comparator.comparing(Staff::getName));
        Set<Staff> painters = new TreeSet<>(Comparator.comparing(Staff::getName));
        Set<Staff> editors = new TreeSet<>(Comparator.comparing(Staff::getName));
        Set<FilmStaff> staffSet = filmDTO.getFilmStaffs();
        staffSet.stream().filter((x)->x.getRole().getName().equals("Режиссёр")).forEach((x)->directors.add(x.getStaff()));
        staffSet.stream().filter((x)->x.getRole().getName().equals("Продюссер")).forEach((x)->producers.add(x.getStaff()));
        staffSet.stream().filter((x)->x.getRole().getName().equals("Актёр")).forEach((x)->actors.add(x.getStaff()));
        staffSet.stream().filter((x)->x.getRole().getName().equals("Сценарист")).forEach((x)->screenwriters.add(x.getStaff()));
        staffSet.stream().filter((x)->x.getRole().getName().equals("Оператор")).forEach((x)->operators.add(x.getStaff()));
        staffSet.stream().filter((x)->x.getRole().getName().equals("Композитор")).forEach((x)->composers.add(x.getStaff()));
        staffSet.stream().filter((x)->x.getRole().getName().equals("Художник")).forEach((x)->painters.add(x.getStaff()));
        staffSet.stream().filter((x)->x.getRole().getName().equals("Монтаж")).forEach((x)->painters.add(x.getStaff()));
        model.addAttribute("img",filmDTO.getStringImg(700,200));
        model.addAttribute("film",filmDTO);
        model.addAttribute("directors",directors);
        model.addAttribute("producers",producers);
        model.addAttribute("actors",actors);
        model.addAttribute("operators",operators);
        model.addAttribute("screenwriters",screenwriters);
        model.addAttribute("composers",composers);
        model.addAttribute("painters",painters);
        model.addAttribute("editors",editors);
        model.addAttribute("genres",filmDTO.getGenres());
        model.addAttribute("staffs",filmDTO.getFilmStaffs());
        model.addAttribute("grades",filmDTO.getGrades());
        model.addAttribute("reviews",filmDTO.getReviews());
        model.addAttribute("id",filmDTO.getId());
        model.addAttribute("user",user);
        return "film";
    }
    @PostMapping("/saveGrade")
    public String saveGrade(@AuthenticationPrincipal User user,@RequestParam(required = true) Long id, Integer grade){
        filmService.addGrade(grade,id,user);
        return "redirect:/film/" + id;
    }

}
