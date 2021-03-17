package com.vsu.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;
    @Column
    private String email;
    @Column
    private String password;
    @ManyToOne(targetEntity = Role.class)
    @JoinColumn(name="role",referencedColumnName = "id")
    private Role role;
    @OneToMany(mappedBy = "user")
    private List<Grade> grades;
    @OneToMany(mappedBy = "author")
    private List<Review> reviews;

}
