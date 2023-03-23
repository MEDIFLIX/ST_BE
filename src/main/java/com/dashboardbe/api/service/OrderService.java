package com.dashboardbe.api.service;

import com.dashboardbe.api.dto.*;

import java.util.List;

public interface OrderService {

    List<String> selectMemberHospital();

    List<String> selectMemberDepartment();

    List<Long> selectContent();

    List<WeeklyVisitsDTO> selectWeeklyVisits();

    List<Long> selectContentChanges();

}
