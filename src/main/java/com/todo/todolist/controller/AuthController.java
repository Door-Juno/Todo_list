package com.todo.todolist.controller;

import com.todo.todolist.domain.User;
import com.todo.todolist.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "회원가입 완료!";
    }

    @PostMapping("/login")
    public String login(@RequestBody User loginUser, HttpSession session){
        User user = userRepository.findByUsername(loginUser.getUsername()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자"));
        if(!passwordEncoder.matches(loginUser.getPassword(), user.getPassword())){
            // 비밀번호 틀림
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }
        session.setAttribute("user", user.getId());
        return "로그인 성공!";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "로그아웃 되었습니다.";
    }
}
