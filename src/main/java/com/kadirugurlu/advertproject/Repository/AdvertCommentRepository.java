package com.kadirugurlu.advertproject.Repository;

import com.kadirugurlu.advertproject.Entity.AdvertComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertCommentRepository extends JpaRepository<AdvertComment, Long> {
}
