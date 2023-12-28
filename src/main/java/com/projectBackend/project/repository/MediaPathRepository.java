package com.projectBackend.project.repository;

import com.projectBackend.project.entity.Community;
import com.projectBackend.project.entity.MediaPaths;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MediaPathRepository extends JpaRepository<MediaPaths, Long> {
    List<MediaPaths> findByCommunity(Community community);

}
