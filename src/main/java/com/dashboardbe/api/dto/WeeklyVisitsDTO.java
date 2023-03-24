package com.dashboardbe.api.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class WeeklyVisitsDTO {

    private Long newbie;
    private Long all;
    private Long quit;
    private Long ContentsHits;

    @QueryProjection
    public WeeklyVisitsDTO(Long all, Long newbie, Long contentsHits, Long quit) {
        this.all = all;
        this.newbie = newbie;
        this.ContentsHits = contentsHits;
        this.quit = quit;
    }

}
