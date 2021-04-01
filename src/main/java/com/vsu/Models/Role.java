package com.vsu.Models;

import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="roles")
@Getter
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
