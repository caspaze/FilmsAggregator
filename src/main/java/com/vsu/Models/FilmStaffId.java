package com.vsu.Models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class FilmStaffId implements Serializable {
    @Column
    private Long film;
    @Column
    private Long staff;
    @Column
    private Long role;
}
