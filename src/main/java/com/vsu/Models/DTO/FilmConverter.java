package com.vsu.Models.DTO;

import com.vsu.Models.Film;

public class FilmConverter {
    public Film filmDtoToFilm(FilmDTO filmDTO){
        return Film.builder()
                .id(filmDTO.getId())
                .name(filmDTO.getName())
                .date(filmDTO.getDate())
                .type(filmDTO.getType())
                .genre(filmDTO.getGenre())
                .country(filmDTO.getCountry())
                .rating(filmDTO.getRating())
                .build();

    }
    public FilmDTO filmToFilmDto(Film film){
        return FilmDTO.builder()
                .id(film.getId())
                .name(film.getName())
                .date(film.getDate())
                .type(film.getType())
                .genre(film.getGenre())
                .country(film.getCountry())
                .rating(film.getRating())
                .build();
    }
}
