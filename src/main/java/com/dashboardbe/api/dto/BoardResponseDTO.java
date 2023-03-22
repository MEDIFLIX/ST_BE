package com.dashboardbe.api.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class BoardResponseDTO {
    private Long id;
    private String name;
    private String content;
    private LocalDate createDate;

    @Builder
    public BoardResponseDTO(Long id, String name, String content, LocalDate createDate) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.createDate = createDate;
    }
}
