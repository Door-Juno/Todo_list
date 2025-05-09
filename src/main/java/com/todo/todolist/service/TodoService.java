package com.todo.todolist.service;

import com.todo.todolist.domain.Todo;
import com.todo.todolist.domain.User;
import com.todo.todolist.repository.TodoRepository;
import com.todo.todolist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public List<Todo> getTodos(Long userId){
        return todoRepository.findByUserId(userId);
    }

    public Todo addTodo(Long userId, Todo todo){
        User user = userRepository.findById(userId).orElseThrow(()-> new IllegalArgumentException("유저 없음"));
        todo.setUser(user);
        return todoRepository.save(todo);
    }

    public void deleteTodo(Long todoId){
        todoRepository.deleteById(todoId);
    }

    public Todo updateTodo(Long todoId,Todo updateTodo){
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new IllegalArgumentException("할 일 없음"));
        todo.setContent(updateTodo.getContent());
        todo.setDueDate(updateTodo.getDueDate());
        return todoRepository.save(todo);
    }

    public void toggleTodo(Long todoId){
        Todo todo = todoRepository.findById(todoId).orElseThrow(()->new IllegalArgumentException("할 일 없음"));

        todo.setCompleted(!todo.isCompleted());

        todoRepository.save(todo);
    }
}

