package com.projectBackend.project.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "USER")
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(name = "USER_PW", nullable = false)
    private String userPw;

    @Column(name = "USER_NICKNAME", nullable = false)
    private String userNickname;

    @Column(name = "USER_AUTH", nullable = false)
    private String userAuth;

    @Column(name = "USER_POINT")
    private Integer userPoint;

    @Column(name = "USER_PHONE", nullable = false)
    private String userPhone;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @Column(name = "USER_ADDR", nullable = false)
    private String userAddr;

    @Column(name = "USER_GEN")
    private char userGen;

    @Column(name = "USER_AGE")
    private Integer userAge;

    @Column(name = "BUSINESS_NUM")
    private String businessNum;

}

