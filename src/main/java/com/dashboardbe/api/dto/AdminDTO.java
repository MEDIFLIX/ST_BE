package com.dashboardbe.api.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class AdminDTO {
    private String id;
    private String pwd;
    private String name;
    private String phoneNumber;
    private String role;

    @Builder
    public AdminDTO(String id, String pwd, String name, String phoneNumber, String role) {
        this.id = id;
        this.pwd = pwd;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }
}