package com.example.makhzan.Service;

import com.example.makhzan.Api.ApiException;
import com.example.makhzan.DTO.CustomerDTO;
import com.example.makhzan.DTO.MediaDTO;
import com.example.makhzan.Model.*;
import com.example.makhzan.Repository.LandLordRepository;
import com.example.makhzan.Repository.MediaRepository;
import com.example.makhzan.Repository.StorageRepository;
import com.example.makhzan.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

//نتاكد ان اللي جالس يعدل هو نفسه صاحب الميديا
public class MediaService {
    private final MediaRepository mediaRepository;
    private final LandLordRepository landLordRepository;
    private final StorageRepository storageRepository;

    public List<Media> getAllLMedias(){
        return mediaRepository.findAll();
    }

    public void add(MediaDTO mediaDTO){
        Storage storage=storageRepository.findStorageById(mediaDTO.getStorage_id());
        if(storage==null){
            throw new ApiException("Storage Not Found You Cant Create Media");
        }
        Media media=new Media(null, mediaDTO.getUrl(), mediaDTO.getType(), storage);
        mediaRepository.save(media);
    }
//userid
    public void updateMedia(MediaDTO mediaDTO,Integer id,Integer userId){
        Media oldMedia=mediaRepository.findMediaById(id);
        if (oldMedia==null){
            throw new ApiException("Media Not Found");
        }
        Landlord landlord=landLordRepository.findLandLordById(userId); /////////////////////////////////////////
        if (landlord == null) {
            throw new ApiException("User Not Found");
        }
        if(!oldMedia.getStorage().getLandlord().getId().equals(userId)){
            throw new ApiException("User is not allowed to update");
        }
        oldMedia.setUrl(mediaDTO.getUrl());
        oldMedia.setType(mediaDTO.getType());
        mediaRepository.save(oldMedia);
    }
    public void deleteMedia(Integer id, Integer userId){
        Media media=mediaRepository.findMediaById(id);
        if(media==null){
            throw new ApiException("Media Not Found");
        }
        Landlord landlord = landLordRepository.findLandLordById(userId);
        if(landlord == null) throw  new ApiException("User not found");
        if(!media.getStorage().getLandlord().getId().equals(userId)){
            throw new ApiException("User is not allowed to delete");
        }
        Storage storage=storageRepository.findStorageById(id);
        storage.setMedias(null); /////////////
        mediaRepository.delete(media);
    }
}
