package com.dashboardbe.api.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class WeeklyVisitsDTO {

    private Long newbie;
    private Long all;
    private Long ContentsHits;
    private Long quit;

    @QueryProjection
    public WeeklyVisitsDTO(Long all, Long newbie, Long contentsHits, Long quit) {
        this.all = all;
        this.newbie = newbie;
        this.ContentsHits = contentsHits;
        this.quit = quit;
    }
}
