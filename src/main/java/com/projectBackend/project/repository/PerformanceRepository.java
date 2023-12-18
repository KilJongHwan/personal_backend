package com.projectBackend.project.repository;

import com.projectBackend.project.entity.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerformanceRepository extends JpaRepository<Performance, Long> {
    // 길종환
//    @Query("SELECT distinct p FROM Performance p JOIN FETCH p.performer pr JOIN FETCH pr.member")
//    List<Performance> findAllWithPerformerAndMember();
}
