package com.kadirugurlu.advertproject.Repository;

import com.kadirugurlu.advertproject.Entity.AdvertField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertFieldRepository extends JpaRepository<AdvertField, Long> {

    List<AdvertField> findByAdvertId(Long advertId);
}
