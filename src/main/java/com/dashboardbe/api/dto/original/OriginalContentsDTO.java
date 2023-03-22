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
        private String category;
        private String hits;
        private String uploadDate;

        @QueryProjection
        public Res(String title, String hits, String category, String uploadDate) {
            this.title = title;
            this.hits = hits;
            this.category = category;
            this.uploadDate = uploadDate;
        }

    }

}
