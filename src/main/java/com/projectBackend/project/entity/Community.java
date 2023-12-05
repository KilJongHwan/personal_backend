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
@Table(name = "community")
@NoArgsConstructor
public class Community {
    @Id
    @Column(name = "COMMUNITY_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long communityId;

    private LocalDateTime regDate;

    @PrePersist
    public void prePersist(){
        regDate = LocalDateTime.now();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "COMMUNITY_DATE", nullable = false)
    private LocalDateTime communityDate;

    @Column(name = "COMMUNITY_TEXT", nullable = false)
    private String communityText;

    @Column(name = "COMMUNITY_CNT", nullable = false)
    private int communityCnt;

    @Column(name = "CATEGORY", nullable = false)
    private String category;
}
