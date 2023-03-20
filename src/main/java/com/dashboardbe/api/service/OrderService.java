package com.dashboardbe.api.service;

import com.dashboardbe.api.dto.*;

import java.util.List;

public interface OrderService {

    List<MemberOrderHospitalDTO> selectMemberHospital();

    List<MemberOrderDepartmentDTO> selectMemberDepartment();

    List<ContentsOrderDTO> selectContent();

    List<WeeklyVisitsDTO> selectWeeklyVisits();

    List<ContentsChangesDTO.Res> selectContentChanges();

}
