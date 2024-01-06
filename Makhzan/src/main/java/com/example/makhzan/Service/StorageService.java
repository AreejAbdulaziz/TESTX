package com.example.makhzan.Service;

import com.example.makhzan.Api.ApiException;
import com.example.makhzan.Model.Landlord;
import com.example.makhzan.Model.Media;
import com.example.makhzan.Model.Storage;
import com.example.makhzan.Repository.LandLordRepository;
import com.example.makhzan.Repository.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StorageService {

    private final StorageRepository storageRepository;
    private final LandLordRepository landLordRepository;
    public List<Storage> getStorages(){
        return storageRepository.findAll();
    }

    public void addStorage(Storage storage, Integer userid){
        Landlord landlord = landLordRepository.findLandLordById(userid);
        if(landlord == null) throw  new ApiException("User not found");
        storage.setStatus("PENDING");
        storage.setLandlord(landlord);
        storageRepository.save(storage);
    }

    public void updateStorage(Integer id, Integer userid, Storage storage){
        Storage oldStorage = storageRepository.findStorageById(id);
        if(oldStorage == null) throw  new ApiException("Storage not found");
        Landlord landlord = landLordRepository.findLandLordById(userid);
        if (landlord == null) throw new ApiException("User not found");
        if(!oldStorage.getLandlord().equals(landlord)) throw new ApiException("User is not allowed to update");
        storage.setId(id);
        storageRepository.save(storage);
    }

    public void deleteStorage(Integer id , Integer userid){
        Storage storage = storageRepository.findStorageById(id);
        if(storage == null) throw  new ApiException("Storage not found");
        Landlord landlord = landLordRepository.findLandLordById(userid);
        if(landlord == null) throw  new ApiException("User not found");
        if(!storage.getLandlord().equals(landlord)) throw new ApiException("User not allowed to delete");
        storageRepository.delete(storage);
    }
    //
    public void verifyStorage(Integer landlordId,Integer storageId){
        Landlord landlord=landLordRepository.findLandLordById(landlordId);
        if(landlord==null){
            throw new ApiException("Landlord not found");
        }
        Storage storage=storageRepository.findStorageById(storageId);
        if(storage==null){
            throw new ApiException("Storage not found");
        }
        if(storage.getMedias()==null){
            throw new ApiException("No media found");
        }
        for(Media m:storage.getMedias()){
            if(m.getType().equals("DOCUMENT")){
                storage.setStatus("Verified");
            }
        }
    }
    //
    public List<Storage> findsStoragesByCity(String city){
        return storageRepository.findStorageByCity(city);
    }
    //
    public List<Storage> findsStoragesByAddress(String address){
        return storageRepository.findStorageByAddress(address);
    }
    //
    public List<Storage> findsStoragesByRentTimes(){
        return storageRepository.findAllStorageByRentTimesDesc();
    }
    //
    public List<Storage> findsStoragesBySize(String size){
        return storageRepository.findStorageBySize(size);
    }

    public List<Storage> findAllStorageByHigh(){
        return storageRepository.findAllStorageByHigh();
    }
}
