package com.dashboardbe.api.repository;

import com.dashboardbe.domain.ContentsAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentsAnalysisRepository extends JpaRepository<ContentsAnalysis, Long> {

}
