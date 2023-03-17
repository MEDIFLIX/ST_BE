package com.dashboardbe.api.controller;

import com.dashboardbe.aop.LoginCheck;
import com.dashboardbe.api.dto.TodoRequestDTO;
import com.dashboardbe.api.repository.AdminRepository;
import com.dashboardbe.api.service.TodolistService;
import com.dashboardbe.common.SessionUtil;
import com.dashboardbe.common.response.BaseResponseBody;
import com.dashboardbe.domain.Admin;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TodolistController {

    private final TodolistService todolistService;
    private final AdminRepository adminRepository;
    @PostMapping("/memo")
    @LoginCheck
    public ResponseEntity<BaseResponseBody<String>> addMemo(@RequestBody TodoRequestDTO dto, HttpSession session) {
        log.info("Asdfadsf");
        String loginId = SessionUtil.getLoginId(session);
        Optional<Admin> optionalAdmin = adminRepository.findById(loginId);
        // 올바른 관리자라면
        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();
            todolistService.save(dto, admin);
            return new ResponseEntity<BaseResponseBody<String>>(
                    new BaseResponseBody<String>(
                            HttpStatus.OK.value(),
                            "성공",
                            loginId
                    ),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<BaseResponseBody<String>>(
                    new BaseResponseBody<String>(
                            HttpStatus.NOT_FOUND.value(),
                            "존재하지 않는 Admin ID입니다.",
                            loginId
                    ),
                    HttpStatus.NOT_FOUND
            );
        }
    }
}
