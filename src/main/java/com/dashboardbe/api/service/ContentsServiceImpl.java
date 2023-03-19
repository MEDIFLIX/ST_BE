package com.dashboardbe.api.service;

import com.dashboardbe.api.repository.ContentsAnalysisRepository;
import com.dashboardbe.api.repository.ContentsRepository;
import com.dashboardbe.domain.Contents;
import com.dashboardbe.domain.ContentsAnalysis;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContentsServiceImpl implements ContentsService{

    private final ContentsRepository contentsRepository;
    private final ContentsAnalysisRepository contentsAnalysisRepository;

    /**
     * 조회수 증가 + 콘텐츠 조회 기록 저장 서비스 로직
     */
    @Override
    public void add(Contents contents) {
        // 조회수 1 증가시키고 저장
        contents.setHits(contents.getHits() + 1);
        contentsRepository.save(contents);

        // 콘텐츠 조회 기록 저장
        ContentsAnalysis contentsAnalysis = ContentsAnalysis.builder()
                .contents(contents)
                .category(contents.getCategory())
                .build();
        contentsAnalysisRepository.save(contentsAnalysis);
    }
}
