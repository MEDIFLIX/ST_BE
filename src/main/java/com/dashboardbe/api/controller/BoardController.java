package com.dashboardbe.api.controller;

import com.dashboardbe.aop.LoginCheck;
import com.dashboardbe.api.dto.BoardRequestDTO;
import com.dashboardbe.api.dto.BoardResponseDTO;
import com.dashboardbe.api.repository.AdminRepository;
import com.dashboardbe.api.service.BoardService;
import com.dashboardbe.common.SessionUtil;
import com.dashboardbe.common.response.BaseResponseBody;
import com.dashboardbe.domain.Admin;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Tag(name = "Board Controller", description = "게시판 컨트롤러")
public class BoardController {

    private final BoardService boardService;
    private final AdminRepository adminRepository;

    /**
     * 게시물 작성 컨트롤러
     */
    @Operation(summary = "[테스트용] 게시물 작성 API", description = "게시물 작성")
    @PostMapping("/board")
    @LoginCheck
    public ResponseEntity<BaseResponseBody<String>> addMemo(@RequestBody BoardRequestDTO dto, HttpSession session) {
        String loginId = SessionUtil.getLoginId(session);
        Optional<Admin> optionalAdmin = adminRepository.findById(loginId);
        // 올바른 관리자라면
        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();
            boardService.save(dto.getContent(), admin);
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

    /**
     * 게시물 리스트 컨트롤러
     */
    @Operation(summary = "[게시판] 게시물 리스트 API", description = "게시물 리스트")
    @GetMapping("/board/list")
    @LoginCheck
    public ResponseEntity<BaseResponseBody<List<BoardResponseDTO>>> list(HttpSession session) {
        String loginId = SessionUtil.getLoginId(session);
        Optional<Admin> optionalAdmin = adminRepository.findById(loginId);
        // 올바른 관리자라면
        if (optionalAdmin.isPresent()) {
            List<BoardResponseDTO> list = boardService.list();
            return new ResponseEntity<BaseResponseBody<List<BoardResponseDTO>>>(
                    new BaseResponseBody<List<BoardResponseDTO>>(
                            HttpStatus.OK.value(),
                            "성공",
                            list
                    ),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<BaseResponseBody<List<BoardResponseDTO>>>(
                    new BaseResponseBody<List<BoardResponseDTO>>(
                            HttpStatus.NOT_FOUND.value(),
                            "존재하지 않는 Admin ID입니다.",
                            null
                    ),
                    HttpStatus.NOT_FOUND
            );
        }
    }
}
