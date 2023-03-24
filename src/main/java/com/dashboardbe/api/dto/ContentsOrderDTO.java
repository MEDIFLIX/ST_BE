package com.dashboardbe.api.dto;

import com.dashboardbe.domain.Category;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ContentsOrderDTO {

    private Category category;
    private Long count;
}
