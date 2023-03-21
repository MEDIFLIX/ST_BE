package com.dashboardbe.api.dto.original;

import lombok.AllArgsConstructor;
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

    @Getter
    @Setter
    @AllArgsConstructor
    public class Res {
        private String title;
        private String count;
        private String creDate;
    }

}
