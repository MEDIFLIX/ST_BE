package com.dashboardbe.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin admin;

    private String content;
    private LocalDateTime localDateTime;

    @Builder
    public Board(Long id, Admin admin, String content, LocalDateTime localDateTime) {
        this.id = id;
        this.admin = admin;
        this.content = content;
        this.localDateTime = localDateTime;
    }
}
