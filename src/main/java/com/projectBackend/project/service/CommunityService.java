package com.projectBackend.project.service;

import com.projectBackend.project.dto.CommunityDTO;
import com.projectBackend.project.entity.Community;
import com.projectBackend.project.entity.CommunityCategory;
import com.projectBackend.project.entity.Member;
import com.projectBackend.project.repository.CommunityCategoryRepository;
import com.projectBackend.project.repository.CommunityRepository;
import com.projectBackend.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityService {
    private final CommunityRepository communityRepository;
    private final MemberRepository memberRepository;
    private final CommunityCategoryRepository categoryRepository;

    public boolean saveCommunity(CommunityDTO communityDTO) {
        try{
            Community community = new Community();
            Member member = memberRepository.findByEmail(communityDTO.getEmail()).orElseThrow(
                    () -> new RuntimeException("해당 회원이 존재하지 않습니다.")
            );
            CommunityCategory category = categoryRepository.findById(communityDTO.getCategoryId()).orElseThrow(
                    () -> new RuntimeException("해당 카테고리가 존재하지 않습니다.")
            );
            community.setTitle(communityDTO.getTitle());
            community.setCategory(category);
            community.setContent(communityDTO.getContent());
            community.setMediaPaths(communityDTO.getMedias());
            community.setMember(member);
            communityRepository.save(community);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public List<CommunityDTO> getCommunityList(){
        List<Community> communities = communityRepository.findAll();
        List<CommunityDTO> communityDTOS = new ArrayList<>();
        for (Community community : communities){
            communityDTOS.add(convertEntityToDTO(community));
        }
        return communityDTOS;
    }
    public CommunityDTO getCommunityDetail(Long id) {
        Community community = communityRepository.findById(id).orElseThrow(
                () -> new RuntimeException("해당 게시물이 존재하지 않습니다.")
        );
        return convertEntityToDTO(community);
    }
    public boolean modifyCommunity(Long id, CommunityDTO communityDTO){
        try {
            Community community = communityRepository.findById(id).orElseThrow(
                    () -> new RuntimeException("해당 게시글이 존재하지 않습니다.")
            );
            community.setTitle(communityDTO.getTitle());
            community.setContent(communityDTO.getContent());
            community.setMediaPaths(communityDTO.getMedias());
            communityRepository.save(community);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // 게시글 삭제
    public boolean deleteCommunity(Long id) {
        try {
            communityRepository.deleteById(id);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    // 게시글 검색
    public List<CommunityDTO> searchCommunity(String keyword){
        List<Community> communities = communityRepository.findByTitleContaining(keyword);
        List<CommunityDTO> communityDTOS = new ArrayList<>();
        for (Community community : communities) {
            communityDTOS.add(convertEntityToDTO(community));
        }
        return communityDTOS;
    }
    // 게시글 페이징
    public List<CommunityDTO> getCommunityList(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        List<Community> communities= communityRepository.findAll(pageable).getContent();
        List<CommunityDTO> communityDTOS = new ArrayList<>();
        for (Community community : communities) {
            communityDTOS.add(convertEntityToDTO(community));
        }
        return communityDTOS;
    }
    // 페이지 수 조회
    public int getCommunity(Pageable pageable){
        return communityRepository.findAll(pageable).getTotalPages();
    }
    // 게시글 엔티티를 DTO로 변환
    private CommunityDTO convertEntityToDTO(Community community) {
        CommunityDTO communityDTO = new CommunityDTO();
        communityDTO.setCommunityId(community.getCommunityId());
        communityDTO.setTitle(community.getTitle());
        communityDTO.setContent(community.getContent());
        communityDTO.setMedias(community.getMediaPaths());
        communityDTO.setEmail(community.getMember().getEmail());
        communityDTO.setRegDate(community.getRegDate());
        return communityDTO;
    }
}
