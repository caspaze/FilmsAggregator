package com.vsu.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "grades")
public class Grade {
    @EmbeddedId
    private GradeId gradeId;
    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("user")
    @JoinColumn(name="user",referencedColumnName = "id")
    private User user;
    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("film")
    @JoinColumn(name="film",referencedColumnName = "id")
    private Film film;
    @Column
    private Integer grade;
}
