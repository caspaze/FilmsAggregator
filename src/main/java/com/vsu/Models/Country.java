package com.vsu.Models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
}
