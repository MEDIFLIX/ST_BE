package com.dashboardbe.api.dto.original;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class OriginalWeeklyInfoDTO {

    @Getter
    @Setter
    @AllArgsConstructor
    public class Req {

    }

    @Getter
    @Setter
    @AllArgsConstructor
    public class Res {
        private Integer totCount;
        private Integer maxCount;
        private String department;
        private String hospital;
    }

}
