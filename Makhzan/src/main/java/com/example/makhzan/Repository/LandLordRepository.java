package com.example.makhzan.Repository;

import com.example.makhzan.Model.Landlord;
import com.example.makhzan.Model.Landlord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LandLordRepository extends JpaRepository<Landlord,Integer> {

    Landlord findLandLordById(Integer id);
}
