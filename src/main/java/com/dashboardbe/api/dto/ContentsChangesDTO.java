package com.dashboardbe.api.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class ContentsChangesDTO {

    @Data
    public class Req {
        private Integer thisWeek;
        private Integer pastWeek;

        @QueryProjection
        public Req(Integer thisWeek, Integer pastWeek) {
            this.thisWeek = thisWeek;
            this.pastWeek = pastWeek;
        }

    }

    @Data
    public class Res {
        private Integer changes;

        @QueryProjection
        public Res(Integer changes) {
            this.changes = changes;
        }

    }

}
