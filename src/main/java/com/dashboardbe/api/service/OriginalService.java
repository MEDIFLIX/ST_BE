package com.dashboardbe.api.service;

import com.dashboardbe.api.dto.MemberOrderDepartmentDTO;
import com.dashboardbe.api.dto.MemberOrderHospitalDTO;
import com.dashboardbe.api.dto.original.OriginalContentsDTO;
import com.dashboardbe.api.dto.original.OriginalWeeklyInfoDTO;

import java.util.List;

public interface OriginalService {

    List<OriginalWeeklyInfoDTO.Res> selectWeeklyInfo();

    List<OriginalContentsDTO.Res> selectContentsInfo();

}
