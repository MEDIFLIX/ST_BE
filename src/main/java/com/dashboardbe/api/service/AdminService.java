package com.dashboardbe.api.service;

import com.dashboardbe.api.dto.LoginDTO;
import com.dashboardbe.api.dto.PageResponseDTO;
import com.dashboardbe.api.dto.AdminDTO;
import org.springframework.data.domain.Pageable;

public interface AdminService {

    void save(AdminDTO adminDTO);
    String login(LoginDTO loginDTO);
    PageResponseDTO list(Pageable pageable);
}
