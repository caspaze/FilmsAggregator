package com.vsu.Models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="types")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
}
