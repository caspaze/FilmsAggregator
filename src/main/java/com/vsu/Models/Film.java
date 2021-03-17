package com.vsu.Models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "films")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Film implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "type", referencedColumnName = "id")
    private Type type;
    @ManyToOne
    @JoinColumn(name = "country", referencedColumnName = "id")
    private Country country;
    @Column
    private Double rating;
    @Column
    private Integer budget;
    @Column
    private Integer duration;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "film_genres",
            joinColumns = @JoinColumn(name = "film"),
            inverseJoinColumns = @JoinColumn(name = "genre"))
    private Set<Genre> genres;
    @OneToMany(mappedBy = "film")
    private Set<FilmStaff> filmStaffs;
    @OneToMany(mappedBy = "film")
    private List<Grade> grades;
    @OneToMany(mappedBy = "film")
    private List<Review> reviews;

}


