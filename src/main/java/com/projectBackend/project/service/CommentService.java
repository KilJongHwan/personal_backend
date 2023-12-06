package com.projectBackend.project.service;

import com.projectBackend.project.dto.CommentDTO;
import com.projectBackend.project.entity.Comment;
import com.projectBackend.project.entity.Community;
import com.projectBackend.project.entity.Member;
import com.projectBackend.project.repository.CommentRepository;
import com.projectBackend.project.repository.CommunityRepository;
import com.projectBackend.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommunityRepository communityRepository;
    private final MemberRepository memberRepository;

    // 댓글 등록
    public boolean commentRegister(CommentDTO commentDTO){
        try {
            Comment comment = new Comment();
            Community board = communityRepository.findById(commentDTO.getCommunityId()).orElseThrow(
                    ()-> new RuntimeException("해당 게시글이 존재하지 않습니다.")
            );
            Member member = memberRepository.findByEmail(commentDTO.getEmail()).orElseThrow(
                    ()-> new RuntimeException("해당 회원이 존재하지 않습니다.")
            );
            Comment parentComment = null;
            if (commentDTO.getParentCommentId() != null) {
                parentComment = commentRepository.findById(commentDTO.getParentCommentId()).orElseThrow(
                        () -> new RuntimeException("해당 부모 댓글이 존재하지 않습니다.")
                );
            }
            comment.setContent(commentDTO.getContent());
            comment.setParentComment(parentComment);
            comment.setMember(member);
            comment.setCommunity(board);
            commentRepository.save(comment);
            return true;

        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // 댓글 수정
    public boolean commentModify(CommentDTO commentDto) {
        try {
            Comment comment = commentRepository.findById(commentDto.getCommentId()).orElseThrow(
                    () -> new RuntimeException("해당 댓글이 존재하지 않습니다.")
            );
            comment.setContent(commentDto.getContent());
            commentRepository.save(comment);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // 댓글 삭제
    public boolean commentDelete(Long commentId) {
        try {
            Comment comment = commentRepository.findById(commentId).orElseThrow(
                    () -> new RuntimeException("해당 댓글이 존재하지 않습니다.")
            );
            commentRepository.delete(comment);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // 댓글 목록 조회
    public List<CommentDTO> getCommentList(Long boardId) {
        try {
            Community community = communityRepository.findById(boardId).orElseThrow(
                    () -> new RuntimeException("해당 게시글이 존재하지 않습니다.")
            );
            List<Comment> comments = commentRepository.findByCommunity(community);
            List<CommentDTO> commentDtos = new ArrayList<>();
            for (Comment comment : comments) {
                commentDtos.add(convertEntityToDto(comment));
            }
            return commentDtos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    // 댓글 검색
    public List<CommentDTO> getCommentList(String keyword) {
        List<Comment> comments = commentRepository.findByContentContaining(keyword);
        List<CommentDTO> commentDtos = new ArrayList<>();
        for (Comment comment : comments) {
            commentDtos.add(convertEntityToDto(comment));
        }
        return commentDtos;
    }

    // 댓글 엔티티를 DTO로 변환
    private CommentDTO convertEntityToDto(Comment comment) {
        CommentDTO commentDto = new CommentDTO();
        commentDto.setCommentId(comment.getCommentId());
        commentDto.setCommunityId(comment.getCommunity().getCommunityId());
        commentDto.setEmail(comment.getMember().getEmail());
        commentDto.setContent(comment.getContent());
        commentDto.setRegDate(comment.getRegDate());
        List<CommentDTO> childComments = new ArrayList<>();
        for (Comment childComment : comment.getChildComments()) {
            childComments.add(convertEntityToDto(childComment));
        }
        commentDto.setChildComments(childComments);
        return commentDto;
    }
}
