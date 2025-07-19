package com.lohith.Job.Application.Reviews;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("companies/{companyId}")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getReviewsByCompanyId(
            @PathVariable("companyId") Long companyId
    ){
        List<Review > reviews = reviewService.getReviews(companyId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("reviews/{reviewId}")
    public ResponseEntity<Review> getReviewById(
            @PathVariable("reviewId") Long reviewId,
            @PathVariable("companyId") Long companyId
    ){
        Review r=reviewService.getReviewByCompanyIdAndReviewId(reviewId,companyId);
        if(r!=null){
            return new ResponseEntity<>(r, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> createReview(
            @PathVariable("companyId") Long companyId,
            @RequestBody Review review
    ){
        boolean created = reviewService.createReview(companyId,review);
        if(created){
            return new ResponseEntity<>("Review Created successfully", HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>("Company not found to add a review",HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(
            @PathVariable("companyId") Long companyId,
            @PathVariable("reviewId") Long reviewId,
            @RequestBody Review review
    ){
        boolean updated = reviewService.updateReview(companyId,reviewId,review);
        if(updated)
            return new ResponseEntity<>("Review Updated successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Not found to update a review",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(
            @PathVariable("reviewId") Long reviewId,
            @PathVariable("companyId") Long companyId
    ){
        boolean deleted = reviewService.deleteReview(companyId,reviewId);
        if(deleted)
            return new ResponseEntity<>("Review Deleted Successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Not found to delete a review",HttpStatus.NOT_FOUND);
    }


}
