package com.projectBackend.project.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CommunityDTO {
    private Long communityId;
    private String email;
    private Long categoryId;
    private String title;
    private String content;
    private List<String> medias;
    private LocalDateTime regDate;
}
