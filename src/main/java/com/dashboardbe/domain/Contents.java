package com.dashboardbe.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Contents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contents_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(value = EnumType.STRING)
    private Category category;

    private String title;
    private String thumbnail;

    private Long hits;
    private LocalDateTime uploadDate;

    @Builder
    public Contents(Long id, Member member, Category category, String title, String thumbnail, Long hits, LocalDateTime uploadDate) {
        this.id = id;
        this.member = member;
        this.category = category;
        this.title = title;
        this.thumbnail = thumbnail;
        this.hits = hits;
        this.uploadDate = uploadDate;
    }
}
