package com.dashboardbe.api.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginDTO {
    private String id;
    private String pwd;

    @Builder
    public LoginDTO(String id, String pwd) {
        this.id = id;
        this.pwd = pwd;
    }
}
