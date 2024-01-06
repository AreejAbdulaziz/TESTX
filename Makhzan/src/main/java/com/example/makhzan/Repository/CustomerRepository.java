package com.example.makhzan.Repository;

import com.example.makhzan.Model.Customer;
import com.example.makhzan.Model.Orders;
import com.example.makhzan.Model.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findCustomerById(Integer id);

    @Query("select c from Customer c order by  c.rentedTimes DESC")
    List<Customer> findCustomerByRentTimesDesc();

    @Query("select o from Orders o where o.status=?1")
    List<Orders> findAllByOrderStatus(String status);

}
