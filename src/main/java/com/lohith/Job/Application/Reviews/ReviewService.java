package com.lohith.Job.Application.Reviews;

import java.util.List;

public interface ReviewService {
    List<Review> getReviews(Long companyId);
    boolean createReview(Long companyId,Review review);
    Review getReviewByCompanyIdAndReviewId(Long reviewId,Long companyId);
    boolean updateReview(Long companyId,Long reviewId,Review review);
    boolean deleteReview(Long companyId,Long reviewId);
}
