package com.dashboardbe.api.service;

import com.dashboardbe.api.dto.ContentsOrderDTO;
import com.dashboardbe.api.dto.MemberOrderDTO;

import java.util.List;

public interface OrderService {

    List<MemberOrderDTO> selectMember();

    List<ContentsOrderDTO> selectContent(String id);

}
