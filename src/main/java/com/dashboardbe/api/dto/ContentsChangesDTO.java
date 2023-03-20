package com.dashboardbe.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class ContentsChangesDTO {

    @Getter
    @Setter
    @AllArgsConstructor
    public class Req {
        private Integer thisWeek;
        private Integer pastWeek;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public class Res {
        private Integer changes;
    }

}
