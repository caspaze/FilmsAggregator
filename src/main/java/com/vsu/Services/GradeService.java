package com.vsu.Services;

import com.vsu.Models.Film;
import com.vsu.Models.Grade;
import com.vsu.Models.User;
import com.vsu.Repository.GradeRepository;
import com.vsu.dto.FilmDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GradeService {
    private final GradeRepository gradeRepository;
    public Page<Grade> findGrades(User user, Pageable pageable){
        Page<Grade> grades = gradeRepository.findAllByUserIs(user,pageable);
        return grades;
    }
    public Grade findUserGrade(User user, Film film){
        return gradeRepository.findByUserAndFilm(user,film);
    }
}
