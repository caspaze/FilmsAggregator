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

import java.util.ArrayList;
import java.util.Set;

@Controller
@RequestMapping("/film")
@AllArgsConstructor
public class FilmController {
    private final RepositoryFilmService repositoryFilmService;
    @GetMapping("/{id}")
    public String getFilm(@PathVariable Integer id, Model model){
        FilmDTO filmDTO = repositoryFilmService.findById(id);
        model.addAttribute("film",filmDTO);
        model.addAttribute("type",filmDTO.getType());
        model.addAttribute("genres",filmDTO.getGenres());
        //Genre[] genres = (Genre[]) filmDTO.getGenres().toArray();
        return "film";
    }

}
