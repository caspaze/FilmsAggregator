package com.vsu.Services;

import com.vsu.Models.*;
import com.vsu.Repository.GradeRepository;
import com.vsu.Repository.ReviewRepository;
import com.vsu.dto.FilmConverter;
import com.vsu.dto.FilmDTO;
import com.vsu.Repository.FilmRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Service
@AllArgsConstructor
public class FilmService {
    private final FilmRepository filmRepository;
    private final GradeRepository gradeRepository;
    private final FilmConverter filmConverter;
    private final ReviewRepository reviewRepository;
    public FilmDTO findById(Long id){
        Optional<Film> film = filmRepository.findById(id);
        try{
            setDefaultImage(film.get());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public List<FilmDTO> findLastFilms(){
        List<Film> films = filmRepository.findLastFilms();
        List<FilmDTO> filmDTOS = new ArrayList<>();
        for(Film film:films){
            filmDTOS.add(filmConverter.filmToFilmDto(film));
        }
        return filmDTOS;
    }
    public void setDefaultImage(Film film) throws IOException {
        if(film.getImage()==null){
            ByteArrayOutputStream baos = new ByteArrayOutputStream(1000);
            BufferedImage image = ImageIO.read(new File("src/main/resources/static/images/defaultImage.png"));
            ImageIO.write(image, "png", baos);
            baos.flush();
            String base64String = Base64.getEncoder().encodeToString(baos.toByteArray());
            byte[] resByteArray = Base64.getDecoder().decode(base64String);
            baos.close();
            Byte[] byteArr = new Byte[resByteArray.length];
            for(int i=0;i< resByteArray.length;i++){
                byteArr[i]=resByteArray[i];
            }
            film.setImage(byteArr);
        }
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
