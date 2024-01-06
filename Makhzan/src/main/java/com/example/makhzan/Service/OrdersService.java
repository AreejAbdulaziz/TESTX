package com.example.makhzan.Service;

import com.example.makhzan.Api.ApiException;
import com.example.makhzan.DTO.OrdersDTO;
import com.example.makhzan.Model.*;
import com.example.makhzan.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final StorageRepository storageRepository;
    private final CustomerRepository customerRepository;

    public List<Orders> getOrders(){
       return ordersRepository.findAll();
    }

    public void createOrder(OrdersDTO ordersDTO){
        Storage storage = storageRepository.findStorageById(ordersDTO.getStorageid());
        if(storage == null) throw new ApiException("Storage not found");
        Customer customer = customerRepository.findCustomerById(ordersDTO.getUserid());
        if(customer == null) throw new ApiException("User not found");
        if(!storage.getAvailable()) throw new ApiException("Storage not available");
        long days = ChronoUnit.DAYS.between(ordersDTO.getStartDate(),ordersDTO.getEndDate());
        Double total = days * storage.getPrice();
        Orders order = new Orders(null,total,ordersDTO.getStartDate(),ordersDTO.getEndDate(),"PENDING", LocalDate.now(),customer,storage);
        ordersRepository.save(order);
    }

//    public void updateOrder(Integer id, Integer userid,OrdersDTO ordersDTO){
//        Orders oldOrder = ordersRepository.findOrdersById(id);
//        if(oldOrder == null) throw new ApiException("Order not found");
//        Customer customer = customerRepository.findCustomerById(userid);
//        if(customer == null) throw new ApiException("User not found");
//        if(!oldOrder.getCustomer().equals(customer)) throw new ApiException("User not allowed to update the order");
//        oldOrder.set
//
//    }

    public void deleteOrder(Integer id, Integer userid){
        Orders order = ordersRepository.findOrdersById(id);
        if(order== null) throw new ApiException("Order not found");
        Customer customer = customerRepository.findCustomerById(userid);
        if(customer == null) throw new ApiException("User not found");
        if(!order.getCustomer().equals(customer)) throw new ApiException("User not allowed");
        ordersRepository.delete(order);
    }

}
