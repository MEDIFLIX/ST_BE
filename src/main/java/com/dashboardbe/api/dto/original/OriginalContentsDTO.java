package com.dashboardbe.api.dto.original;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class OriginalContentsDTO {

    @Getter
    @Setter
    @AllArgsConstructor
    public class Req {
        private String countYn;
        private String searchWord;
    }

    @Data
    public class Res {
        private String title;
        private String count;
        private String creDate;

        @QueryProjection
        public Res(String title, String count, String creDate) {
            this.title = title;
            this.count = count;
            this.creDate = creDate;
        }

    }

}
