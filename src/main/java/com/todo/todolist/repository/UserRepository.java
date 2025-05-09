package com.todo.todolist.repository;

import com.todo.todolist.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // 로그인시 username으로 사용자를 찾을 수 있도록 추가
    Optional<User> findByUsername(String username);
}