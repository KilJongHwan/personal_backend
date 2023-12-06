package com.projectBackend.project.repository;

import com.projectBackend.project.constant.Authority;
import com.projectBackend.project.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@SpringBootTest
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Transactional
    public void testInsertMember() {
        Member member = Member.builder()
                .email("test@email.com")
                .password("testPassword")
                .nickName("testNickName")
                .name("testName")
                .addr("testAddr")
                .tel("010-1234-5678")
                .gender("M")
                .BUSINESS_NUM("123-45-67890")
                .point(0)
                .age(30)
                .authority(Authority.ROLE_USER)
                .build();

        memberRepository.save(member);
    }
}