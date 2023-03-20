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
public class ContentsAnalysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contents_id")
    private Contents contents;

    @Enumerated(value = EnumType.STRING)
    private Category category;

    private LocalDateTime visitDate;

    @Builder
    public ContentsAnalysis(Contents contents, Category category) {
        this.contents = contents;
        this.category = category;
        this.visitDate = LocalDateTime.now();
    }
}
