package com.dashboardbe.api.controller;

import com.dashboardbe.aop.LoginCheck;
import com.dashboardbe.api.dto.TodoRequestDTO;
import com.dashboardbe.api.dto.TodoResponseDTO;
import com.dashboardbe.api.repository.AdminRepository;
import com.dashboardbe.api.repository.TodolistRepository;
import com.dashboardbe.api.service.TodolistService;
import com.dashboardbe.common.SessionUtil;
import com.dashboardbe.common.response.BaseResponseBody;
import com.dashboardbe.domain.Admin;
import com.dashboardbe.domain.Todolist;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class TodolistController {

    private final TodolistService todolistService;
    private final AdminRepository adminRepository;
    private final TodolistRepository todolistRepository;

    @PostMapping("/memo")
    @LoginCheck
    public ResponseEntity<BaseResponseBody<String>> addMemo(@RequestBody TodoRequestDTO dto, HttpSession session) {
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

    @GetMapping("/memo/delete")
    @LoginCheck
    public ResponseEntity<BaseResponseBody<String>> deleteMemo(@RequestParam Long todolistId, HttpSession session) {
        String loginId = SessionUtil.getLoginId(session);
        Optional<Admin> optionalAdmin = adminRepository.findById(loginId);
        // 올바른 관리자라면
        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();
            Optional<Todolist> optionalTodolist = todolistRepository.findById(todolistId);
            if (optionalTodolist.isPresent()) {
                Todolist todolist = optionalTodolist.get();
                todolistService.delete(todolist, admin);
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
                                "존재하지 않는 메모입니다.",
                                todolistId.toString()
                        ),
                        HttpStatus.NOT_FOUND
                );
            }
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

    @GetMapping("/memo/list")
    @LoginCheck
    public ResponseEntity<BaseResponseBody<List<TodoResponseDTO>>> list(HttpSession session) {
        String loginId = SessionUtil.getLoginId(session);
        Optional<Admin> optionalAdmin = adminRepository.findById(loginId);
        // 올바른 관리자라면
        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();
            List<TodoResponseDTO> list = todolistService.list(admin);
            return new ResponseEntity<BaseResponseBody<List<TodoResponseDTO>>>(
                    new BaseResponseBody<List<TodoResponseDTO>>(
                            HttpStatus.OK.value(),
                            "성공",
                            list
                    ),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<BaseResponseBody<List<TodoResponseDTO>>>(
                    new BaseResponseBody<List<TodoResponseDTO>>(
                            HttpStatus.NOT_FOUND.value(),
                            "존재하지 않는 Admin ID입니다.",
                            null
                    ),
                    HttpStatus.NOT_FOUND
            );
        }
    }
}
