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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "admin_id")
    private Admin admin;

    private String content;
    private LocalDateTime createDate;

    @Builder
    public Board(Admin admin, String content) {
        this.admin = admin;
        this.content = content;
        this.createDate = LocalDateTime.now();
    }
}
