package com.vsu.Repository;

import com.vsu.Models.Review;
import com.vsu.Models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository {
    Review findReviewByUser(User user);
}
