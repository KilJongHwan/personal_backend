package com.projectBackend.project.controller;

import com.projectBackend.project.dto.CommentDTO;
import com.projectBackend.project.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    // 댓글 등록
    @PostMapping("/new")
    public ResponseEntity<Boolean> commentRegister(@RequestBody CommentDTO commentDTO) {
        log.info("commentDto: {}", commentDTO);
        boolean result = commentService.commentRegister(commentDTO);
        return ResponseEntity.ok(result);
    }
    // 댓글 수정
    @PutMapping("/modify")
    public ResponseEntity<Boolean> commentModify(@RequestBody CommentDTO commentDTO) {
        boolean result = commentService.commentModify(commentDTO);
        return ResponseEntity.ok(result);
    }
    // 댓글 삭제
    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<Boolean> commentDelete(@PathVariable Long commentId) {
        boolean result = commentService.commentDelete(commentId);
        return ResponseEntity.ok(result);
    }
    // 댓글 목록 조회
    @GetMapping("/list/{boardId}")
    public ResponseEntity<List<CommentDTO>> commentList(@PathVariable Long boardId) {
        log.info("boardId: {}", boardId);
        List<CommentDTO> list = commentService.getCommentList(boardId);
        return ResponseEntity.ok(list);
    }
    // 댓글 목록 페이징
    // 댓글 상세 조회
    // 댓글 검색
    @GetMapping("/search")
    public ResponseEntity<List<CommentDTO>> commentSearch(@RequestParam String keyword) {
        List<CommentDTO> list = commentService.getCommentList(keyword);
        return ResponseEntity.ok(list);
    }
}
