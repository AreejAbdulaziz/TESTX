package com.example.makhzan.Controller;

import com.example.makhzan.Model.Storage;
import com.example.makhzan.Model.User;
import com.example.makhzan.Service.StorageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/makhzan/storage")
public class StorageController {
    private final StorageService storageService;

    @GetMapping("/get")
    public ResponseEntity getStorages(){
        return ResponseEntity.status(200).body(storageService.getStorages());
    }

    @PostMapping("/add/{userid}")
    public ResponseEntity addStorage(@RequestBody@Valid Storage storage, @PathVariable Integer userid){
        storageService.addStorage(storage, userid);
        return ResponseEntity.status(200).body("Storage added");
    }

    @PutMapping("/update/{id}/{userid}")
    public ResponseEntity updateStorage(@RequestBody @Valid Storage storage, @PathVariable Integer id, @PathVariable Integer userid){
        storageService.updateStorage(id, userid, storage);
        return ResponseEntity.status(200).body("Storage updated");
    }

    @DeleteMapping("/delete/{id}/{userid}")
    public ResponseEntity deleteStorage(@PathVariable Integer id,@PathVariable Integer userid){
        storageService.deleteStorage(id, userid);
        return ResponseEntity.status(200).body("Storage deleted");
    }

    //
    @GetMapping("/getB")
    public ResponseEntity rentTimes(){
        return ResponseEntity.status(200).body(storageService.findsStoragesByRentTimes());
    }
    //
    @GetMapping("/getByc/{city}")
    public ResponseEntity findsStoragesByCity(@PathVariable String city){
        return ResponseEntity.status(200).body(storageService.findsStoragesByCity(city));
    }

    //
    @GetMapping("/getBya/{address}")
    public ResponseEntity findsStoragesByAddress(@PathVariable String address){
        return ResponseEntity.status(200).body(storageService.findsStoragesByAddress(address));
    }

    //
    @GetMapping("/getBys/{size}")
    public ResponseEntity findsStoragesBySize(@PathVariable String size){
        return ResponseEntity.status(200).body(storageService.findsStoragesBySize(size));
    }

    //
    @PutMapping("/verify/{landlordId}/{storageId}")
    public ResponseEntity verifyStorage(@PathVariable Integer landlordId,@PathVariable Integer storageId){
        storageService.verifyStorage(landlordId, storageId);
        return ResponseEntity.status(200).body("Storage Verified");
    }

    @GetMapping("/getByH")
    public ResponseEntity findAllStorageByHigh(){
        return ResponseEntity.status(200).body(storageService.findAllStorageByHigh());
    }
}
