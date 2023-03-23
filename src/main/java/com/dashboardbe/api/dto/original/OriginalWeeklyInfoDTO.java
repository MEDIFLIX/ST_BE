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

        private Long totCount;
        private Long maxCount;
        private Long department;
        private Integer hospital;

        @QueryProjection
        public Res(Long totCount, Long maxCount, Long department, Integer hospital) {
            this.totCount = totCount;
            this.maxCount = maxCount;
            this.department = department;
            this.hospital = hospital;
        }

    }

}
