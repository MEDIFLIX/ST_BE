package com.dashboardbe.batch.job.dailyPopular;

import com.dashboardbe.batch.dto.ContentsDTO;
import com.dashboardbe.batch.repository.DailyRepository;
import com.dashboardbe.common.response.LocalDateTimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@StepScope
public class DailyPopularTasklet implements Tasklet {
    @Value("#{jobParameters[from]}")
    private String fromString;
    @Value("#{jobParameters[to]}")
    private String toString;
    private final DailyRepository dailyRepository;

    public DailyPopularTasklet(DailyRepository dailyRepository) {
        this.dailyRepository = dailyRepository;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        final LocalDateTime from = LocalDateTimeUtils.parse(fromString);
        final LocalDateTime to = LocalDateTimeUtils.parse(toString);

        final List<ContentsDTO> contentsDTOList = dailyRepository.findByStatisticsAtBetweenAndGroupBy(from, to);

        return RepeatStatus.FINISHED;

    }
}

