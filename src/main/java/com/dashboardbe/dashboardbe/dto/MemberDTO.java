package com.dashboardbe.dashboardbe.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MemberDTO {
    private Long id;
    private String memberId;
    private String memberPassword;
    private String memberName;

    @Builder
    public MemberDTO(String memberId, String memberPassword, String memberName) {
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.memberName = memberName;
    }
}