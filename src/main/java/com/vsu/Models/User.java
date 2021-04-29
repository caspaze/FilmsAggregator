package com.vsu.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private Boolean enabled;
    @Column
    private String username;
    @ManyToOne(targetEntity = Role.class,cascade = {CascadeType.PERSIST})
    @JoinColumn(name="role",referencedColumnName = "id")
    private Role role;
    @OneToMany(mappedBy = "user",cascade = {CascadeType.ALL})
    private List<Grade> grades;
    @OneToMany(mappedBy = "author",cascade = {CascadeType.ALL})
    private List<Review> reviews;
    @Column(name = "registration_date")
    private LocalDate registrationDate;
    @Column
    private Byte[] image;
    @ManyToOne
    @JoinColumn(name = "country", referencedColumnName = "id")
    private Country country;
    @Column
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate birthdate;
    @Column(name = "vk_link")
    private String vkLink;
    @Column(name = "twitter_link")
    private String twitterLink;
    @Column(name = "google_link")
    private String googleLink;
    @Column(name = "activation_code")
    private String activationCode;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        return roleSet;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
