package com.dashboardbe.batch.job.dailyPopular;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DailyPopularJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public AddDailyJobConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job addDailyJob() {
        return this.jobBuilderFactory.get("addPassesJob")
                .start(addDailyStep())
                .build();
    }


    @Bean
    public Step addDailyStep() {
        return this.stepBuilderFactory.get("addPassesStep")
                .tasklet()
                .build();
    }

}
