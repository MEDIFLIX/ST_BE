package com.dashboardbe.api.service;

import com.dashboardbe.api.dto.BoardResponseDTO;
import com.dashboardbe.domain.Admin;

import java.util.List;

public interface BoardService {

    void save (String content, Admin admin);
    List<BoardResponseDTO> list();
}
