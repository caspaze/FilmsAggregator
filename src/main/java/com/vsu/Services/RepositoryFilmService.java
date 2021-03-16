package com.vsu.Services;

import com.vsu.dto.FilmConverter;
import com.vsu.dto.FilmDTO;
import com.vsu.Models.Film;
import com.vsu.Repository.FilmRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RepositoryFilmService {
    private final FilmRepository filmRepository;
    private final FilmConverter filmConverter;

    public FilmDTO findById(Integer id){
        Optional<Film> film = filmRepository.findById(id);
        return filmConverter.filmToFilmDto(film.get());
    }



}
