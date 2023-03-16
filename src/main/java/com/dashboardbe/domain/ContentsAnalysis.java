package com.dashboardbe.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class ContentsAnalysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contents_id")
    private Contents contents;

    private LocalDateTime visitDate;

    @Builder
    public ContentsAnalysis(Long id, Contents contents, LocalDateTime visitDate) {
        this.id = id;
        this.contents = contents;
        this.visitDate = visitDate;
    }
}
