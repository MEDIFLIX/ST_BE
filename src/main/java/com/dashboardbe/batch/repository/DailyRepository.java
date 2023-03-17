package com.dashboardbe.batch.repository;

import com.dashboardbe.batch.dto.ContentsDTO;
import com.dashboardbe.domain.Contents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface DailyRepository extends JpaRepository<Contents, Integer> {

    @Query(value = "SELECT c.category, c.title, c.hits" +
            "         FROM Contents c " +
            "        WHERE c.uploadDate BETWEEN :from AND :to " +
            "     GROUP BY c.id")
    List<ContentsDTO> findByStatisticsAtBetweenAndGroupBy(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);

}
