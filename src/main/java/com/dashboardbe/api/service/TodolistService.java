package com.dashboardbe.api.service;

import com.dashboardbe.api.dto.TodoRequestDTO;
import com.dashboardbe.api.repository.TodolistRepository;
import com.dashboardbe.domain.Admin;
import com.dashboardbe.domain.Todolist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodolistService {
    private final TodolistRepository todolistRepository;
    public void save(TodoRequestDTO dto, Admin admin) {
        Todolist todolist = Todolist.builder()
                .admin(admin)
                .content(dto.getContent())
                .build();
        todolistRepository.save(todolist);
    }
}
