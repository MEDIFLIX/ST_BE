package com.dashboardbe.api.service;

import com.dashboardbe.api.dto.AdminResponseDTO;
import com.dashboardbe.api.dto.LoginDTO;
import com.dashboardbe.api.dto.AdminDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminService {

    void save(AdminDTO adminDTO);
    String login(LoginDTO loginDTO);
    List<AdminResponseDTO> list();
}
