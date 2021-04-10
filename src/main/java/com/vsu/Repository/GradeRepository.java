package com.vsu.Repository;

import com.vsu.Models.Grade;
import com.vsu.Models.GradeId;
import com.vsu.Models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, GradeId> {
    Page<Grade> findAllByUserIs(User user, Pageable pageable);
}
