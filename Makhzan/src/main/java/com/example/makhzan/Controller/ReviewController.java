package com.example.makhzan.Controller;

import com.example.makhzan.DTO.ReviewDTO;
import com.example.makhzan.Service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/makhzan/review")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/get")
    public ResponseEntity getReviews(){
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.getReviews());
    }
    @PostMapping("/add")
    public ResponseEntity addReview(@RequestBody @Valid ReviewDTO reviewDTO){
        reviewService.addReview(reviewDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Review added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateReview(@PathVariable Integer id, @RequestBody @Valid ReviewDTO reviewDTO){
        reviewService.updateReview(id,reviewDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Review updated");
    }
    @DeleteMapping("/delete/{id}/{userid}")
    public ResponseEntity deleteReivew(@PathVariable Integer id , @PathVariable Integer userid){
        reviewService.deleteReview(id,userid);
        return ResponseEntity.status(HttpStatus.OK).body("Review deleted");
    }
}
