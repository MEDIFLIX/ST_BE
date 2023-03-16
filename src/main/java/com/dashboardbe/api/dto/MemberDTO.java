package com.dashboardbe.api.dto;

import com.dashboardbe.domain.MedicalDepartment;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MemberDTO {
    private String id;

    private String pwd;
    private String name;
    private String phoneNumber;

    private MedicalDepartment medicalDepartment;

    private String hospital;

    @Builder
    public MemberDTO(String id, String pwd, String name, String phoneNumber, MedicalDepartment medicalDepartment, String hospital) {
        this.id = id;
        this.pwd = pwd;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.medicalDepartment = medicalDepartment;
        this.hospital = hospital;
    }
}