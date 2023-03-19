package com.dashboardbe.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Admin {
    @Id @Column(name = "admin_id")
    private String id;

    private String pwd;
    private String name;
    private String phoneNumber;
    private String role;
    private LocalDateTime createTime;

    @Builder
    public Admin(String id, String pwd, String name, String phoneNumber, String role) {
        this.id = id;
        this.pwd = pwd;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.createTime = LocalDateTime.now();
    }
}
