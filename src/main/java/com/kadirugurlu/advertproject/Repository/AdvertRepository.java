package com.kadirugurlu.advertproject.Repository;

import com.kadirugurlu.advertproject.Entity.Advert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertRepository extends JpaRepository<Advert, Long> {

    List<Advert> findByUserId(Long userId);
    List<Advert> findByCategoryId(Long categoryId);
    List<Advert> findByGroupsId(Long groupId);
}
