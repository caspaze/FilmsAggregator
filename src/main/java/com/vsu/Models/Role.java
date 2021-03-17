package com.vsu.Models;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="roles")
@Getter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;

}
