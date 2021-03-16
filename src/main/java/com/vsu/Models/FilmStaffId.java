package com.vsu.Models;

import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class FilmStaffId implements Serializable {
    @Column
    private Integer film;
    @Column
    private Integer staff;
}
