package com.dashboardbe.api.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TodoRequestDTO {
    private String content;

    @Builder
    public TodoRequestDTO(String content) {
        this.content = content;
    }
}
