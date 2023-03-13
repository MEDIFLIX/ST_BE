package com.dashboardbe.dashboardbe.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MemberDTO {
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;

    @Builder
    public MemberDTO(Long id, String memberEmail, String memberPassword, String memberName) {
        this.id = id;
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberName = memberName;
    }
}