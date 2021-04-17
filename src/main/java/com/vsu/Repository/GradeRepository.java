package com.vsu.Repository;

import com.vsu.Models.Grade;
import com.vsu.Models.GradeId;
import com.vsu.Models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<Grade, GradeId> {
    Page<Grade> findAllByUserIs(User user, Pageable pageable);
    Integer findAllByUser(User user);
    @Query("SELECT avg(g.grade) from Grade g where g.user= ?1")
    Double findAvgUserGrade(User user);
   /* @Query(value = "SELECT avg(g.grade) from Grade g where g.user=?1 and ",nativeQuery = true)
    Double findCartoonAvgGrade(User user);*/

}