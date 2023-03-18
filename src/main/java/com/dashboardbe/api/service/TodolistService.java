package com.dashboardbe.api.service;

import com.dashboardbe.api.dto.TodoRequestDTO;
import com.dashboardbe.api.dto.TodolistDTO;
import com.dashboardbe.api.repository.TodolistRepository;
import com.dashboardbe.domain.Admin;
import com.dashboardbe.domain.Todolist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public void delete(Todolist todolist, Admin admin) {
        todolistRepository.delete(todolist);
    }

    public List<TodolistDTO> list(Admin admin) {
        List<Todolist> todolists = todolistRepository.findByAdminId(admin.getId());
        List<TodolistDTO> list = new ArrayList<>();
        for (Todolist todolist : todolists) {
            TodolistDTO todolistDTO = TodolistDTO.builder()
                    .id(todolist.getId())
                    .content(todolist.getContent())
                    .build();
            list.add(todolistDTO);
        }
        return list;
    }
}
