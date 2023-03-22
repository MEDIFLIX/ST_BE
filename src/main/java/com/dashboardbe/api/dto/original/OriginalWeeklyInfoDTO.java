package com.dashboardbe.api.dto.original;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class OriginalWeeklyInfoDTO {

    @Getter
    @Setter
    @AllArgsConstructor
    public class Req {

    }

    @Data
    public class Res {

        private Integer totCount;
        private Integer maxCount;
        private String department;
        private String hospital;

        @QueryProjection
        public Res(Integer totCount, Integer maxCount, String department, String hospital) {
            this.totCount = totCount;
            this.maxCount = maxCount;
            this.department = department;
            this.hospital = hospital;
        }

    }

}
