package com.dashboardbe.api.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminResponseDTO {
    private String adminId;
    private String name;
    private String phoneNumber;
    private String role;

    @Builder
    public AdminResponseDTO(String adminId, String name, String phoneNumber, String role) {
        this.adminId = adminId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }
}
