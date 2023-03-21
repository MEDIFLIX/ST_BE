package com.dashboardbe.api.service;

import com.dashboardbe.api.dto.original.OriginalContentsDTO;
import com.dashboardbe.api.dto.original.OriginalWeeklyInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OriginalServiceImpl implements OriginalService {

    @Override
    public List<OriginalWeeklyInfoDTO.Res> selectWeeklyInfo() {
        return null;
    }

    @Override
    public List<OriginalContentsDTO.Res> selectContentsInfo() {
        return null;
    }
}
