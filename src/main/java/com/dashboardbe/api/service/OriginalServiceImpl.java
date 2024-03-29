package com.dashboardbe.api.service;

import com.dashboardbe.api.dto.original.OriginalContentsDTO;
import com.dashboardbe.api.dto.original.WeeklyInfoResponseDTO;
import com.dashboardbe.api.repository.OriginalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OriginalServiceImpl implements OriginalService {

    private final OriginalRepository originalRepository;

    @Override
    public WeeklyInfoResponseDTO selectWeeklyInfo() {

        WeeklyInfoResponseDTO result = originalRepository.selectWeeklyInfo();

        return result;

    }

    @Override
    public List<OriginalContentsDTO.Res> selectContentsInfo(OriginalContentsDTO.Req request) {

        List<OriginalContentsDTO.Res> result = originalRepository.selectContentsInfo(request);

        return result;

    }
}
