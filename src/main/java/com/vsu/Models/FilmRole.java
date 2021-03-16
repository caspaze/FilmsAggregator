package com.vsu.Models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="film_roles")
public class FilmRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
}
