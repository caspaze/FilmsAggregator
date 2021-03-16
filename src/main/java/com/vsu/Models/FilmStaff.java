package com.vsu.Models;

import lombok.EqualsAndHashCode;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "film_staff")
public class FilmStaff {
    @EmbeddedId
    FilmStaffId filmStaffId;
    @ManyToOne
    @MapsId("film")
    private Film film;
    @ManyToOne
    @MapsId("staff")
    private Staff staff;
    @ManyToOne
    @JoinColumn(name="role",referencedColumnName = "id")
    private FilmRole role;


}
