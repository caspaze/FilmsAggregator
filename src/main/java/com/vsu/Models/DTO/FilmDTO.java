package com.vsu.Models.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilmDTO {
    private Integer id;
    private String name;
    private LocalDate date;
    private Integer type;
    private Integer genre;
    private Integer country;
    private Double rating;
}
