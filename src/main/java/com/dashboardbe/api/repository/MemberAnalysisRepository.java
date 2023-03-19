package com.dashboardbe.api.repository;

import com.dashboardbe.domain.MemberAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberAnalysisRepository extends JpaRepository<MemberAnalysis, Long> {

}
