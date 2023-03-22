package com.dashboardbe.api.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class WeeklyVisitsDTO {

    private Integer all;
    private Integer newbie;
    private Integer ContentsHits;
    private Integer quit;

    @QueryProjection
    public WeeklyVisitsDTO(Integer all, Integer newbie, Integer contentsHits, Integer quit) {
        this.all = all;
        this.newbie = newbie;
        this.ContentsHits = contentsHits;
        this.quit = quit;
    }
}
