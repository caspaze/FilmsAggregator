package com.vsu.Models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "staff")
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private LocalDate birthdate;
    @ManyToOne
    @JoinColumn(name="country",referencedColumnName = "id")
    private Country country;
    @OneToMany(mappedBy = "staff")
    private Set<FilmStaff> filmStaffs;

}
