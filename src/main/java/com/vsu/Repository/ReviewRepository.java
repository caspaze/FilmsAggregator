package com.vsu.Repository;

import com.vsu.Models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    @Query(value = "select * from reviews order by date desc limit 8",nativeQuery = true)
    List<Review> findLastReviews();
}
