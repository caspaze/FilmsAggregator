package com.vsu.Models;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name="film_roles")
@Getter
public class FilmRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
