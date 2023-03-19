package com.dashboardbe.api.service;

import com.dashboardbe.api.dto.LoginDTO;
import com.dashboardbe.api.repository.MemberAnalysisRepository;
import com.dashboardbe.api.repository.MemberRepository;
import com.dashboardbe.domain.Member;
import com.dashboardbe.domain.MemberAnalysis;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final MemberAnalysisRepository memberAnalysisRepository;

    /**
     * 로그인 + 방문자 기록 저장 서비스 로직
     */
    @Override
    public String login(LoginDTO loginDTO) {
        /**
         * 1. 회원이 입력한 이메일로 DB에서 조회를 함
         * 2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
         */
        Optional<Member> optionalMember = memberRepository.findById(loginDTO.getId());
        // 해당 이메일을 가진 회원 정보가 있는 경우
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            // 비밀번호 일치
            if (member.getPwd().equals(loginDTO.getPwd())) {
                /**
                 *  로그인 시 방문자 기록 MemberAnalysis DB에 저장
                 */
                MemberAnalysis memberAnalysis = MemberAnalysis.builder()
                        .member(member)
                        .hospital(member.getHospital())
                        .medicalDepartment(member.getMedicalDepartment())
                        .build();
                memberAnalysisRepository.save(memberAnalysis);

                // entity -> dto 변환 후 리턴
                return member.getId();
            }else { // 비밀번호 불일치
                return "WRONG_PWD";
            }
        } else { // 회원이 아닌 경우
            return "NO_DATA";
        }
    }
}
