package com.example.makhzan.Controller;

import com.example.makhzan.DTO.CustomerDTO;
import com.example.makhzan.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
/*
import org.springframework.security.core.annotation.AuthenticationPrincipal;
*/
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/makhzan/customer")
public class CustomerController {
    private final CustomerService customerService;
    //Admin
    @GetMapping("/get")
    public ResponseEntity getAllCustomers() {
        return ResponseEntity.status(200).body(customerService.getAllCustomers());
    }

    //All
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid CustomerDTO customerDTO) {
        customerService.register(customerDTO);
        return ResponseEntity.status(200).body("Customer Register");
    }

    @PutMapping("update/{userId}")
    public ResponseEntity updateCustomer(@RequestBody @Valid CustomerDTO customerDTO, @PathVariable Integer userId){
        customerService.updateCustomer(customerDTO,userId);
        return ResponseEntity.status(200).body("Customer updated");

    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity deleteCustomer(@PathVariable Integer userId){
        customerService.deleteCustomer(userId);
        return ResponseEntity.status(200).body("Customer deleted");
    }

    @GetMapping("/getB")
    public ResponseEntity rentTimes(){
        return ResponseEntity.status(200).body(customerService.findsCustomersByRentTimes());
    }

    @GetMapping("/getMyOrders/{customerId}")
    public ResponseEntity getMyOrders(@PathVariable Integer customerId){
        return ResponseEntity.status(200).body(customerService.getMyOrders(customerId));
    }

    @GetMapping("/getOrders/{status}")
    public ResponseEntity findOrdersByStatus(@PathVariable String status){
        return ResponseEntity.status(200).body(customerService.findOrdersByStatus(status));
    }



}

