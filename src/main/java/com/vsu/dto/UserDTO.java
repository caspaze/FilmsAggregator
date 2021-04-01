package com.vsu.dto;

import com.vsu.Models.Grade;
import com.vsu.Models.Review;
import com.vsu.Models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private Role role;
    private List<Grade> grades;
    private List<Review> reviews;
}
