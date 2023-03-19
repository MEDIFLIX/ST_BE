package com.dashboardbe.api.service;

import com.dashboardbe.api.dto.LoginDTO;

public interface MemberService {

    String login(LoginDTO loginDTO);
}
