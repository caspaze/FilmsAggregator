package com.vsu.Controllers;

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


@Controller
@RequestMapping("/film")
@AllArgsConstructor
public class FilmController {
    private final FilmService filmService;
    @GetMapping("/{id}")
    public String getFilm(@AuthenticationPrincipal User user, @PathVariable Long id, Model model) throws IOException {
        FilmDTO filmDTO = filmService.findById(id);
        model.addAttribute("img",filmDTO.getStringImg(700,200));
        model.addAttribute("film",filmDTO);
        Set<Staff> directors = new TreeSet<>(Comparator.comparing(Staff::getName));
        Set<Staff> producers = new TreeSet<>(Comparator.comparing(Staff::getName));
        Set<Staff> actors = new TreeSet<>(Comparator.comparing(Staff::getName));
        Set<Staff> screenwriters = new TreeSet<>(Comparator.comparing(Staff::getName));
        Set<Staff> operators = new TreeSet<>(Comparator.comparing(Staff::getName));
        Set<Staff> composers = new TreeSet<>(Comparator.comparing(Staff::getName));
        Set<Staff> painters = new TreeSet<>(Comparator.comparing(Staff::getName));
        Set<Staff> editors = new TreeSet<>(Comparator.comparing(Staff::getName));
        Set<FilmStaff> staffSet = filmDTO.getFilmStaffs();
        for(FilmStaff staff: staffSet){
            switch(staff.getRole().getName()){
                case "Режиссёр":{
                    directors.add(staff.getStaff());
                    break;
                }
                case "Продюссер":{
                    producers.add(staff.getStaff());
                    break;
                }
                case "Актёр":{
                    actors.add(staff.getStaff());
                    break;
                }
                case "Сценарист":{
                    screenwriters.add(staff.getStaff());
                    break;
                }
                case "Оператор":{
                    operators.add(staff.getStaff());
                    break;
                }
                case "Композитор":{
                    composers.add(staff.getStaff());
                    break;
                }
                case "Художник":{
                    painters.add(staff.getStaff());
                    break;
                }
                case "Монтаж":{
                    editors.add(staff.getStaff());
                    break;
                }
            }
        }
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
        model.addAttribute("user",user);
        return "film";
    }


}
