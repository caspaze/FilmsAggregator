package com.vsu.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Getter
@Setter
@Table(name = "film_staff")
public class FilmStaff {
    @EmbeddedId
    FilmStaffId filmStaffId;
    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("film")
    @JoinColumn(name="film",referencedColumnName = "id")
    private Film film;
    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("staff")
    @JoinColumn(name="staff",referencedColumnName = "id")
    private Staff staff;
    @ManyToOne
    @MapsId("role")
    @JoinColumn(name = "role",referencedColumnName = "id")
    private FilmRole role;

}
