package com.vsu.Controllers;

import com.vsu.Models.Genre;
import com.vsu.dto.FilmDTO;
import com.vsu.Services.RepositoryFilmService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/film")
@AllArgsConstructor
public class FilmController {
    private final RepositoryFilmService repositoryFilmService;
    @GetMapping("/{id}")
    public String getFilm(@PathVariable Long id, Model model){
        FilmDTO filmDTO = repositoryFilmService.findById(id);
        model.addAttribute("film",filmDTO);
        model.addAttribute("genres",filmDTO.getGenres());
        model.addAttribute("staffs",filmDTO.getFilmStaffs());
        model.addAttribute("grades",filmDTO.getGrades());
        model.addAttribute("reviews",filmDTO.getReviews());
        return "film";
    }

}
