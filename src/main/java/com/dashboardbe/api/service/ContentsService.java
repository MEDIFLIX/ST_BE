package com.dashboardbe.api.service;

import com.dashboardbe.api.repository.ContentsRepository;
import com.dashboardbe.domain.Contents;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContentsService {

    private final ContentsRepository contentsRepository;

    public void add(Contents contents) {
        contents.setHits(contents.getHits() + 1);
        contentsRepository.save(contents);
    }
}
