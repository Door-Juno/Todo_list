package com.todo.todolist.controller;

import com.todo.todolist.domain.Todo;
import com.todo.todolist.service.TodoService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    //할 일 목록 조회
    @GetMapping
    public List<Todo> getTodos(HttpSession session){
        Long userId = (Long)session.getAttribute("userId");
        return todoService.getTodos(userId);
    }

    //할 일 추가
    @PostMapping
    public Todo addTodo(@RequestBody Todo todo, HttpSession session){
        Long userId = (Long)session.getAttribute("userId");
        return todoService.addTodo(userId, todo);
    }

    //할 일 수정
    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable Long id, @RequestBody Todo todo){
        return todoService.updateTodo(id, todo);
    }

    //할 일 삭제
    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id){
        todoService.deleteTodo(id);
    }

    // 완료 여부 토글
    @PatchMapping("/{id}/toggle")
    public void toggleTodo(@PathVariable Long id) {
        todoService.toggleTodo(id);
    }
}
