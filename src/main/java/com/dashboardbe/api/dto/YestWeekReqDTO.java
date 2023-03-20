package com.dashboardbe.api.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class YestWeekReqDTO {

    private LocalDateTime yestDay;
    private LocalDateTime yestWeek;
    private LocalDateTime pastWeek;

}
