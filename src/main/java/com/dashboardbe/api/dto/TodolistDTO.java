package com.dashboardbe.api.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TodolistDTO {
    private Long id;
    private String content;

    @Builder
    public TodolistDTO(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}
