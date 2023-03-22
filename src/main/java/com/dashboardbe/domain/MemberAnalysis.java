package com.dashboardbe.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Data
@NoArgsConstructor
public class MemberAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    Member member;

    @Enumerated(value = EnumType.STRING)
    private MedicalDepartment medicalDepartment;

    private Integer hospital;

    private LocalDateTime visitDate;

    @Builder
    public MemberAnalysis(Member member, MedicalDepartment medicalDepartment, Integer hospital) {
        this.member = member;
        this.medicalDepartment = medicalDepartment;
        this.hospital = hospital;
        this.visitDate = LocalDateTime.now();
    }
}
