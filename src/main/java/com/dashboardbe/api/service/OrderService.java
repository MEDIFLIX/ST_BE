package com.dashboardbe.api.service;

import com.dashboardbe.api.dto.*;

import java.util.List;

public interface OrderService {

    List<Long> selectMemberHospital();

    List<Long> selectMemberDepartment();

    List<Long> selectContent();

    List<WeeklyVisitsDTO> selectWeeklyVisits();

    List<Long> selectContentChanges();

}
