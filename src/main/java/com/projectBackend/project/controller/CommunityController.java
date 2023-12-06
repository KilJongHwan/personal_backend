package com.projectBackend.project.controller;

import com.projectBackend.project.dto.CommunityDTO;
import com.projectBackend.project.service.CommunityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/community")
@RequiredArgsConstructor
public class CommunityController {
    private final CommunityService communityService;
    @PostMapping("/new")
    public ResponseEntity<Boolean> saveCommunity(@RequestBody CommunityDTO communityDTO) {
        return ResponseEntity.ok(communityService.saveCommunity(communityDTO));
    }

    @GetMapping("/list")
    public ResponseEntity<List<CommunityDTO>> getCommunityList() {
        return ResponseEntity.ok(communityService.getCommunityList());
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<CommunityDTO> getCommunityDetail(@PathVariable Long id) {
        return ResponseEntity.ok(communityService.getCommunityDetail(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> modifyCommunity(@PathVariable Long id, @RequestBody CommunityDTO communityDTO) {
        return ResponseEntity.ok(communityService.modifyCommunity(id, communityDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCommunity(@PathVariable Long id) {
        return ResponseEntity.ok(communityService.deleteCommunity(id));
    }
    // 게시글 목록 페이징
    @GetMapping("/list/page")
    public ResponseEntity<List<CommunityDTO>> boardList(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "20") int size) {
        List<CommunityDTO> list = communityService.getCommunityList(page, size);
        return ResponseEntity.ok(list);
    }
}
