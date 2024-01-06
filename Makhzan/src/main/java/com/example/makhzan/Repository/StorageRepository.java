package com.example.makhzan.Repository;

import com.example.makhzan.Model.Landlord;
import com.example.makhzan.Model.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface StorageRepository extends JpaRepository<Storage,Integer> {
    Storage findStorageById(Integer id);

    Set<Storage> findStorageByLandlord(Landlord landlord);

    @Query("select s from Storage s order by  s.rentedTimes DESC")
    List<Storage> findAllStorageByRentTimesDesc();

    List<Storage> findStorageByCity(String city);

    List<Storage> findStorageByAddress(String address);

    List<Storage> findStorageBySize(String size);

    @Query("select s from Storage s order by  s.price DESC")
    List<Storage> findAllStorageByHigh();

}
