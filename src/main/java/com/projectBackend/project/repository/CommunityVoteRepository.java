package com.projectBackend.project.repository;

import com.projectBackend.project.entity.CommunityVote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityVoteRepository extends JpaRepository<CommunityVote, Long> {

}
