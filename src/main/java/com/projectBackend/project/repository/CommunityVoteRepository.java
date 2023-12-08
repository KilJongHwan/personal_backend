package com.projectBackend.project.repository;

import com.projectBackend.project.entity.Community;
import com.projectBackend.project.entity.CommunityView;
import com.projectBackend.project.entity.CommunityVote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommunityVoteRepository extends JpaRepository<CommunityVote, Long> {
    List<CommunityView> findByCommunity(Community community);

}
