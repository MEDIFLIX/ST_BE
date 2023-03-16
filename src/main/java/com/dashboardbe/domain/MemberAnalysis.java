package com.dashboardbe.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    private LocalDateTime visitDate;

    @Builder
    public MemberAnalysis(Long id, Member member, LocalDateTime visitDate) {
        this.id = id;
        this.member = member;
        this.visitDate = visitDate;
    }
}
