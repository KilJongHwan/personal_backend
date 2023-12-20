package com.projectBackend.project.controller;

import com.projectBackend.project.dto.UserResDto;
import com.projectBackend.project.entity.Member;
import com.projectBackend.project.service.AuthService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@Setter
@Getter
@ToString
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final AuthService authService;

    // 로그인 상태 체크 (+ refresh 토큰 유효성 체크)
    @GetMapping("/isLogin")
    public ResponseEntity<Boolean> isLogin(@RequestParam String email) {
        boolean isTrue = authService.isLogined(email);
        System.out.println("로그인 체크 : " + isTrue);
        return ResponseEntity.ok(isTrue);
    }

    // 유저 포인트 충전
    @PostMapping("/increasePoints")
    public ResponseEntity<Boolean> increasePoints(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        int points = Integer.parseInt(payload.get("points"));
        boolean success = authService.increasePoints(email, points);

        if (success) {
            return ResponseEntity.ok().body(true);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
    }
}
