package com.vsu.dto;

import com.vsu.Models.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilmDTO {
    private Long id;
    private String name;
    private LocalDate date;
    private Type type;
    private Country country;
    private Double rating;
    private Integer budget;
    private Integer duration;
    private Set<Genre> genres;
    private Set<FilmStaff> filmStaffs;
    private List<Grade> grades;
    private List<Review> reviews;
}
