package com.dashboardbe.api.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardRequestDTO {
    private String content;

    @Builder
    public BoardRequestDTO(String content) {
        this.content = content;
    }
}
