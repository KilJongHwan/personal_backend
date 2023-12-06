package com.projectBackend.project.repository;

import com.projectBackend.project.entity.Comment;
import com.projectBackend.project.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByContentContaining(String keyword);
    List<Comment> findByCommunity(Community community);
}
