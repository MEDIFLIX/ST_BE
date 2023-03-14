package com.dashboardbe.dashboardbe.repository;

import com.dashboardbe.dashboardbe.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // 아이디로 회원 정보 조회
    Optional<Member> findByMemberId(String memberId);
}
