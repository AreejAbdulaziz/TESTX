package com.example.makhzan.Controller;

import com.example.makhzan.DTO.LandlordDTO;
import com.example.makhzan.DTO.MediaDTO;
import com.example.makhzan.Model.User;
import com.example.makhzan.Service.MediaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/makhzan/media")
public class MediaController {
    private final MediaService mediaService;

    @GetMapping("/get")
    public ResponseEntity getAllMedias() {
        return ResponseEntity.status(200).body(mediaService.getAllLMedias());
    }


    @PostMapping("/add")
    public ResponseEntity add(@RequestBody @Valid MediaDTO mediaDTO) {
        mediaService.add(mediaDTO);
        return ResponseEntity.status(200).body("Media Added");
    }

    @PutMapping("/update/{id}/{userId}")
    public ResponseEntity updateMedia(@RequestBody @Valid MediaDTO mediaDTO,@PathVariable Integer id, @PathVariable Integer userId){
        mediaService.updateMedia(mediaDTO,id,userId);
        return ResponseEntity.status(200).body("Media Updated");

    }

    @DeleteMapping("/delete/{id}/{userId}")
    public ResponseEntity deleteLandlord(@PathVariable Integer id,@PathVariable Integer userId){
        mediaService.deleteMedia(id,userId);
        return ResponseEntity.status(200).body("Media deleted");
    }
}
