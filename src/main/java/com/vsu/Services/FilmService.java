package com.vsu.Services;

import com.vsu.Models.Grade;
import com.vsu.Models.GradeId;
import com.vsu.Models.User;
import com.vsu.Repository.GradeRepository;
import com.vsu.dto.FilmConverter;
import com.vsu.dto.FilmDTO;
import com.vsu.Models.Film;
import com.vsu.Repository.FilmRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
        List<Film> films = filmRepository.findLastFilms().subList(0,4);
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

}
