package com.dashboardbe.dashboardbe.service;

import com.dashboardbe.dashboardbe.domain.Member;
import com.dashboardbe.dashboardbe.dto.MemberDTO;
import com.dashboardbe.dashboardbe.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /***
     * 회원 정보 저장 서비스 로직
     */
    public void save(MemberDTO memberDTO) {
        // dto -> entity 변환
        Member member = Member.builder().
                memberId(memberDTO.getMemberId()).
                memberPassword(memberDTO.getMemberPassword()).
                memberName(memberDTO.getMemberName())
                .build();
        // repository의 save 메소드 호출
        memberRepository.save(member);
    }

    /***
     * 로그인 서비스 로직
     * @return dto -> 로그인 성공
     * @return null -> 로그인 실패
     */
    public MemberDTO login(MemberDTO memberDTO) {
        /**
         * 1. 회원이 입력한 이메일로 DB에서 조회를 함
         * 2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
         */
        Optional<Member> byMemberEmail = memberRepository.findByMemberId(memberDTO.getMemberId());
        // 해당 이메일을 가진 회원 정보가 있는 경우
        if (byMemberEmail.isPresent()) {
            Member member = byMemberEmail.get();
            // 비밀번호 일치
            if (member.getMemberPassword().equals(memberDTO.getMemberPassword())) {
                // entity -> dto 변환 후 리턴
                MemberDTO dto = MemberDTO.builder().
                        memberId(member.getMemberId()).
                        memberPassword(member.getMemberPassword()).
                        memberName(member.getMemberName()).
                        build();
                return dto;
            }else { // 비밀번호 불일치 -> 로그인 실패, null 리턴
                return null;
            }
        } else { // 회원이 아닌 경우 -> 로그인 실패, null 리턴
            return null;
        }
    }
}