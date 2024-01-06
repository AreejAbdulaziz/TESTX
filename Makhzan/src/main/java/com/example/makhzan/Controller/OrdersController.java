package com.example.makhzan.Controller;

import com.example.makhzan.DTO.OrdersDTO;
import com.example.makhzan.Service.OrdersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/makhzan/order")
public class OrdersController {

    private final OrdersService ordersService;

    @GetMapping("/get")
    public ResponseEntity getOrders(){
        return ResponseEntity.status(HttpStatus.OK).body(ordersService.getOrders());
    }
    @PostMapping("/add")
    public ResponseEntity createOrder(@RequestBody @Valid OrdersDTO ordersDTO){
        ordersService.createOrder(ordersDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Order created");
    }
    @DeleteMapping("/delete/{id}/{userid}")
    public ResponseEntity deleteOrder(@PathVariable Integer id, @PathVariable Integer userid){
        ordersService.deleteOrder(id,userid);
        return ResponseEntity.status(HttpStatus.OK).body("Order removed");
    }

}
