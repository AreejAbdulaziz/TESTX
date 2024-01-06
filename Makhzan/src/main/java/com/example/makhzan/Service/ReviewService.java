package com.example.makhzan.Service;

import com.example.makhzan.Api.ApiException;
import com.example.makhzan.DTO.ReviewDTO;
import com.example.makhzan.Model.Customer;
import com.example.makhzan.Model.Orders;
import com.example.makhzan.Model.Review;
import com.example.makhzan.Model.Storage;
import com.example.makhzan.Repository.CustomerRepository;
import com.example.makhzan.Repository.OrdersRepository;
import com.example.makhzan.Repository.ReviewRepository;
import com.example.makhzan.Repository.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final StorageRepository storageRepository;
    private final CustomerRepository customerRepository;
    private final OrdersRepository ordersRepository;
    public List<Review> getReviews(){
        return reviewRepository.findAll();
    }

    public Orders hasOrder(Integer storageid, Integer userid){
        List<Orders> orders = ordersRepository.getOrders(storageid);
        for(Orders orders1:orders){
            if(orders1.getCustomer().getId().equals(userid) && orders1.getStatus().equals("ACCEPTED")){
                return orders1;
            }
        }
        return null;
    }

    public void addReview(ReviewDTO reviewDTO){
        Storage storage = storageRepository.findStorageById(reviewDTO.getStorageid());
        if(storage == null) throw new ApiException("Storage not found");
        Customer customer = customerRepository.findCustomerById(reviewDTO.getUserid());
        if(customer == null) throw new ApiException("User not found");
        Orders order = hasOrder(storage.getId(),customer.getId());
        if(order == null) throw new ApiException("User didn't order");
        Review review = new Review(null, reviewDTO.getRating(), reviewDTO.getComment(),storage,customer);
        reviewRepository.save(review);
    }

    public void updateReview(Integer id, ReviewDTO reviewDTO) {
        Review review = reviewRepository.findReviewById(id);
        if (review == null) throw new ApiException("Review not found");
        Customer customer = customerRepository.findCustomerById(reviewDTO.getUserid());
        if(customer == null) throw new ApiException("User not found");
        if(!review.getCustomer().equals(customer)) throw new ApiException("Customer not allowed to update");
        review.setComment(reviewDTO.getComment());
        review.setRating(reviewDTO.getRating());
        reviewRepository.save(review);
    }

    public void deleteReview(Integer id ,Integer userid){
        Review review = reviewRepository.findReviewById(id);
        if(review == null) throw new ApiException("Review not found");
        Customer customer = customerRepository.findCustomerById(userid);
        if(!review.getCustomer().equals(customer)) throw new ApiException("User not allowed to delete the review");
        reviewRepository.delete(review);
    }





}
