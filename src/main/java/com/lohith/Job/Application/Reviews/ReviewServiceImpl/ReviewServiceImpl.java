package com.lohith.Job.Application.Reviews.ReviewServiceImpl;


import com.lohith.Job.Application.Company.Company;
import com.lohith.Job.Application.Company.CompanyRepository;
import com.lohith.Job.Application.Company.CompanyService;
import com.lohith.Job.Application.Reviews.Review;
import com.lohith.Job.Application.Reviews.ReviewRepository;
import com.lohith.Job.Application.Reviews.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean createReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if (company != null) {
            company.getReviews().add(review);
            companyService.save(company);
            review.setCompany(company); // 🔥 This is critical (I have forgotten this)
            reviewRepository.save(review);
            return true;
        }
        else return false;
    }

    @Override
    public Review getReviewByCompanyIdAndReviewId(Long reviewId, Long companyId) {
        return reviewRepository.findByIdAndCompanyId(reviewId, companyId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found"));
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review review) {
        Review reviewToUpdate=getReviewByCompanyIdAndReviewId(reviewId, companyId);
        if (reviewToUpdate != null) {
            reviewToUpdate.setTitle(review.getTitle());
            reviewToUpdate.setDescription(review.getDescription());
            reviewToUpdate.setRating(review.getRating());

            reviewRepository.save(reviewToUpdate);
            return true;
        }else  return false;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        try {
            Company company = companyService.getCompanyById(companyId);
            Review review = this.getReviewByCompanyIdAndReviewId(reviewId, companyId);

            company.getReviews().remove(review);
            companyService.save(company);
            reviewRepository.delete(review);

            return true;
        } catch (Exception e) {
            return false;
        }
    }



}
