package com.dashboardbe.api.service;

import com.dashboardbe.api.dto.*;
import com.dashboardbe.domain.Category;
import com.dashboardbe.domain.MedicalDepartment;

import java.util.List;

public interface OrderService {

    List<String> selectMemberHospital();

    List<MedicalDepartment> selectMemberDepartment();

    List<Category> selectContent();

    List<WeeklyVisitsDTO> selectWeeklyVisits();

    List<Long> selectContentChanges();

}
