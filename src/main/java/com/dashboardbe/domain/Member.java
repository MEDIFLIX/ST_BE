package com.dashboardbe.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Member {
    @Id @Column(name = "member_id")
    private String id;

    private String pwd;
    private String name;
    private String phoneNumber;

    @Enumerated(value = EnumType.STRING)
    private MedicalDepartment medicalDepartment;

    private Integer hospital;
    private String isMember;
    private LocalDateTime createTime;
    private LocalDateTime deleteTime;

    @Builder
    public Member(String id, String pwd, String name, String phoneNumber, MedicalDepartment medicalDepartment, Integer hospital) {
        this.id = id;
        this.pwd = pwd;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.medicalDepartment = medicalDepartment;
        this.hospital = hospital;
        this.isMember = "Y";
        this.createTime = LocalDateTime.now();
        this.deleteTime = null;
    }
}
