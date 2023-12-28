package com.projectBackend.project.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "mediapaths")
@NoArgsConstructor
public class MediaPaths {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore  // 순환 참조 방지
    @ManyToOne
    private Community community;

    @Lob
    private String path;

}
