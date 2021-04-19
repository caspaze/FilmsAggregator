package com.vsu.Models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class GradeId implements Serializable {
    @Column
    private Long user;
    @Column
    private Long film;
}
