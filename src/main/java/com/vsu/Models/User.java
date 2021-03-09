package com.vsu.Models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
@Data
@Builder
@NoArgsConstructor
public class User {
    @Id
    private Integer id;
    @Column
    private String username;
    @Column
    private String email;
    @Column
    private String password;
}
