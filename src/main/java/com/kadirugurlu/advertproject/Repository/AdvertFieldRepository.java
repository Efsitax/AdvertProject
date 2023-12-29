package com.kadirugurlu.advertproject.Repository;

import com.kadirugurlu.advertproject.Entity.AdvertField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertFieldRepository extends JpaRepository<AdvertField, Long> {
}
