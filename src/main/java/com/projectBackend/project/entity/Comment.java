package com.projectBackend.project.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Table(name = "COMMENT")
@NoArgsConstructor
public class Comment {

    @Id
    @Column(name = "COMMENT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private Member user;

    @Column(name = "COMMENT_DATE", nullable = false)
    private LocalDateTime commentDate;

    @Column(name = "COMMENT_CONTENT", nullable = false)
    private String commentContent;
}
