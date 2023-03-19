package com.dashboardbe.api.service;

import com.dashboardbe.api.dto.ContentsOrderDTO;
import com.dashboardbe.api.dto.MemberOrderDepartmentDTO;
import com.dashboardbe.api.dto.MemberOrderHospitalDTO;
import com.dashboardbe.api.dto.WeeklyVisitsDTO;

import java.util.List;

public interface OrderService {

    List<MemberOrderHospitalDTO> selectMemberHospital();

    List<MemberOrderDepartmentDTO> selectMemberDepartment();

    List<ContentsOrderDTO> selectContent();

    List<WeeklyVisitsDTO> selectWeeklyVisits();

}
