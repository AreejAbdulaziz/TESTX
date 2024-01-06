package com.example.makhzan.Controller;

import com.example.makhzan.DTO.CustomerDTO;
import com.example.makhzan.DTO.LandlordDTO;
import com.example.makhzan.Model.User;
import com.example.makhzan.Service.CustomerService;
import com.example.makhzan.Service.LandLordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/makhzan/landlord")
public class LandlordController {
    private final LandLordService landLordService;

    @GetMapping("/get")
    public ResponseEntity getAllLandlord() {
        return ResponseEntity.status(200).body(landLordService.getAllLandlords());
    }

    //All
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid LandlordDTO landlordDTO) {
        landLordService.register(landlordDTO);
        return ResponseEntity.status(200).body("Landlord Register");
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity updateLandlord(@RequestBody @Valid LandlordDTO landlordDTO, @PathVariable Integer userId){
        landLordService.updateLandlord(landlordDTO,userId);
        return ResponseEntity.status(200).body("Landlord updated");

    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity deleteLandlord(@PathVariable Integer userId){
        landLordService.deleteLandlord(userId);
        return ResponseEntity.status(200).body("Landlord deleted");
    }

    @GetMapping("/getOrders/{storageId}")
    public ResponseEntity ordersForStorage(@PathVariable Integer storageId){
        return ResponseEntity.status(200).body(landLordService.ordersForStorage(storageId));
    }

    @PutMapping("/accept/{storageId}/{orderId}")
    public ResponseEntity acceptOrder(@PathVariable Integer storageId, @PathVariable Integer orderId){
        landLordService.acceptOrder(storageId, orderId);
        return ResponseEntity.status(200).body("Order Accepted");
    }

    @PutMapping("/reject/{storageId}/{orderId}")
    public ResponseEntity rejectOrder(@PathVariable Integer storageId, @PathVariable Integer orderId){
        landLordService.rejectOrder(storageId, orderId);
        return ResponseEntity.status(200).body("Order Accepted");
    }

    @PutMapping("/endPeriod/{storageId}")
    public ResponseEntity endPeriod(@PathVariable Integer storageId){
        landLordService.endPeriod(storageId);
        return ResponseEntity.status(200).body("Order Ended Successfully");
    }
}
