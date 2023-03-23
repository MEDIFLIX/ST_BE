package com.dashboardbe.api.service;

import com.dashboardbe.api.dto.MemberOrderDepartmentDTO;
import com.dashboardbe.api.dto.MemberOrderHospitalDTO;
import com.dashboardbe.api.dto.original.OriginalContentsDTO;
import com.dashboardbe.api.dto.original.OriginalWeeklyInfoDTO;
import com.dashboardbe.api.dto.original.WeeklyInfoResponseDTO;

import java.util.List;

public interface OriginalService {

    WeeklyInfoResponseDTO selectWeeklyInfo();

    List<OriginalContentsDTO.Res> selectContentsInfo(OriginalContentsDTO.Req request);

}
