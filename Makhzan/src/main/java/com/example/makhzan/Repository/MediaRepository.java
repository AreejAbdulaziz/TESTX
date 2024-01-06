package com.example.makhzan.Repository;

import com.example.makhzan.Model.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends JpaRepository<Media,Integer> {

    Media findMediaById(Integer id);

    @Query("select m from Media m where m.type=?1")
    Media findMediaByTypeImage(String IMAGE);

}
