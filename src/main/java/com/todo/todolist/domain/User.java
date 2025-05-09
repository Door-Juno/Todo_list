package com.todo.todolist.domain;

import jakarta.persistence.*;
import lombok.* ;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false) // unique는 중복 여부
    private String username;

    @Column(nullable = false)
    private String password;
}