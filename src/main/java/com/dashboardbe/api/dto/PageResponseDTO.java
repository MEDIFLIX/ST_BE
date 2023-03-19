package com.dashboardbe.api.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@NoArgsConstructor
public class PageResponseDTO {
    Page<AdminResponseDTO> page;

    @Builder
    public PageResponseDTO(Page<AdminResponseDTO> page) {
        this.page = page;
    }
}
