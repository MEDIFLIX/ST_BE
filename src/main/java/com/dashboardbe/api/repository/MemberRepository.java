package com.dashboardbe.api.repository;

import com.dashboardbe.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
    // 아이디로 회원 정보 조회
    Optional<Member> findById(String Id);
}
