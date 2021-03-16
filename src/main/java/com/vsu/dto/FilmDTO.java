package com.vsu.dto;

import com.vsu.Models.Country;
import com.vsu.Models.Genre;
import com.vsu.Models.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilmDTO {
    private Integer id;
    private String name;
    private LocalDate date;
    private Type type;
    private Country country;
    private Double rating;
    private Integer budget;
    private Integer duration;
    private Set<Genre> genres;

}
