package com.dashboardbe.api.controller;

import com.dashboardbe.api.dto.AdminDTO;
import com.dashboardbe.api.service.AdminService;
import com.dashboardbe.common.SessionUtil;
import com.dashboardbe.common.response.BaseResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminService memberService;

    /***
     * 회원가입
     */
    @PostMapping("/admin/save")
    public String save(@RequestBody AdminDTO adminDTO) {
        memberService.save(adminDTO);
        return "성공!";
    }

    /**
     * 로그인
     * @return null이면 로그인 실패
     */
    @PostMapping("/admin/login")
    public ResponseEntity<BaseResponseBody<String>> login(@RequestBody AdminDTO adminDTO, HttpServletRequest request) {
        String loginId = memberService.login(adminDTO);
        // 회원이 아닌 경우
        if (loginId.equals("NO_DATA")) {
            return new ResponseEntity<BaseResponseBody<String>>(
                    new BaseResponseBody<String>(
                    HttpStatus.NOT_FOUND.value(),
                    "회원 정보가 존재하지 않습니다.",
                            "NO_DATA"
            ),
                    HttpStatus.NOT_FOUND
            );
        }
        // 비밀번호가 틀린 경우
        else if (loginId.equals("WRONG_PWD")) {
            return new ResponseEntity<BaseResponseBody<String>>(
                    new BaseResponseBody<String>(
                            HttpStatus.NOT_FOUND.value(),
                            "비밀번호가 틀렸습니다.",
                            "WRONG_PWD"
                    ),
                    HttpStatus.NOT_FOUND
            );
        }
        // 로그인 성공한 경우
        else {
            // 세션이 있으면 존재하는 세션 반환
            // 세션이 없으면 신규 세션을 생성해서 반환
            HttpSession session = request.getSession();
            // 세션에 로그인 아이디 정보 저장
            SessionUtil.setLoginId(session, loginId);
            return new ResponseEntity<BaseResponseBody<String>>(
                    new BaseResponseBody<String>(
                            HttpStatus.OK.value(),
                            "성공",
                            loginId
                    ),
                    HttpStatus.OK
            );
        }
    }

    /**
     * 로그아웃
     */
    @PostMapping("/admin/logout")
    public String logout(HttpServletRequest request) {
        // false 옵션 -> 세션이 없는 경우 새로 생성하지 않도록
        HttpSession session = request.getSession(false);
        // 세션이 존재하는 경우
        if (session != null) {
            session.invalidate(); // 세션 소멸
        }
        // 로그인 화면으로 리다이렉트 시킴
        return "/admin/login";
    }
}