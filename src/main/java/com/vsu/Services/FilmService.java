package com.vsu.Services;

import com.vsu.Models.*;
import com.vsu.Repository.FilmRepository;
import com.vsu.Repository.GradeRepository;
import com.vsu.Repository.ReviewRepository;
import com.vsu.dto.FilmConverter;
import com.vsu.dto.FilmDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FilmService {
    private final FilmRepository filmRepository;
    private final GradeRepository gradeRepository;
    private final FilmConverter filmConverter;
    private final ReviewRepository reviewRepository;
    public FilmDTO findById(Long id){
        Optional<Film> film = filmRepository.findById(id);
        return filmConverter.filmToFilmDto(film.get());
    }
    public Page<FilmDTO> findFilms(String name, Pageable pageable){
        Page<Film> films = filmRepository.findFilmsByNameContainingIgnoreCase(name,pageable);
        List<FilmDTO> filmDTOS = new ArrayList<>();
        for(Film film:films.getContent()){
            filmDTOS.add(filmConverter.filmToFilmDto(film));
        }
        Page<FilmDTO> dtofilms = new PageImpl<>(filmDTOS);
        return dtofilms;
    }

    public List<FilmDTO> findLastFilms() {
        List<Film> films = filmRepository.findLastFilms();
        List<FilmDTO> filmDTOS = new ArrayList<>();
        for (Film film : films) {
            filmDTOS.add(filmConverter.filmToFilmDto(film));
        }
        return filmDTOS;
    }

    public void addGrade(Integer userGrade, Long filmId, User user){
        Film film = filmRepository.findById(filmId).get();
        Grade grade = Grade.builder()
                .gradeId(new GradeId(user.getId(),film.getId()))
                .film(film)
                .user(user)
                .grade(userGrade)
                .date(LocalDate.now())
                .build();
        gradeRepository.save(grade);
        film.setRating(gradeRepository.findAvgGradeByFilm(film.getId()));
        filmRepository.save(film);
    }
    public List<Review> findLastReviews(){
        return reviewRepository.findLastReviews();
    }
    public void addReview(User user,Long id, String title, String text, String reviewType){
        ReviewType reviewTypeE = null;
        switch(reviewType){
            case "Положительная":{
                reviewTypeE = ReviewType.Positive;
                break;
            }
            case "Отрицательная":{
                reviewTypeE = ReviewType.Negative;
                break;
            }
            case "Нейтральная":{
                reviewTypeE = ReviewType.Neutral;
                break;
            }
        }
        Review review = Review.builder()
                .film(filmRepository.findById(id).get())
                .author(user)
                .title(title)
                .text(text)
                .reviewType(reviewTypeE)
                .date(LocalDate.now())
                .build();
        reviewRepository.save(review);
    }

}
