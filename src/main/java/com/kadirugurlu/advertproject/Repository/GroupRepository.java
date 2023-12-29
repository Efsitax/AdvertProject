package com.kadirugurlu.advertproject.Repository;

import com.kadirugurlu.advertproject.Entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}
