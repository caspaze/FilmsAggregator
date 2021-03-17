package com.vsu.dto;

import com.vsu.Models.Film;
import org.springframework.stereotype.Component;

@Component
public class FilmConverter {
    public Film filmDtoToFilm(FilmDTO filmDTO){
        return Film.builder()
                .id(filmDTO.getId())
                .name(filmDTO.getName())
                .date(filmDTO.getDate())
                .type(filmDTO.getType())
                .country(filmDTO.getCountry())
                .rating(filmDTO.getRating())
                .budget(filmDTO.getBudget())
                .duration(filmDTO.getDuration())
                .genres(filmDTO.getGenres())
                .filmStaffs(filmDTO.getFilmStaffs())
                .grades(filmDTO.getGrades())
                .reviews(filmDTO.getReviews())
                .build();
    }
    public FilmDTO filmToFilmDto(Film film){
        return FilmDTO.builder()
                .id(film.getId())
                .name(film.getName())
                .date(film.getDate())
                .type(film.getType())
                .country(film.getCountry())
                .rating(film.getRating())
                .budget(film.getBudget())
                .duration(film.getDuration())
                .genres(film.getGenres())
                .filmStaffs(film.getFilmStaffs())
                .grades(film.getGrades())
                .reviews(film.getReviews())
                .build();
    }
}
