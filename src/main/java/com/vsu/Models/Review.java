package com.vsu.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reviews")
@Getter
@Setter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="film",referencedColumnName = "id")
    private Film film;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="author",referencedColumnName = "id")
    private User author;
    @Column
    private String title;
    @Column
    private String text;
    @Column
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ReviewType reviewType;
}
