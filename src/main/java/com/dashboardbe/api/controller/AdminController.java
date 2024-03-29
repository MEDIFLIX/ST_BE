package com.dashboardbe.api.controller;

import com.dashboardbe.api.dto.AdminDTO;
import com.dashboardbe.api.dto.AdminResponseDTO;
import com.dashboardbe.api.dto.LoginDTO;
import com.dashboardbe.api.repository.AdminRepository;
import com.dashboardbe.api.service.AdminService;
import com.dashboardbe.common.SessionUtil;
import com.dashboardbe.common.response.BaseResponseBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:3000",  allowedHeaders = "*", allowCredentials = "true")
@CrossOrigin(origins = "*",  allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/admin")
@Tag(name = "Admin Controller", description = "관리자 컨트롤러")
public class AdminController {

    private final AdminService adminService;
    private final AdminRepository adminRepository;
    /***
     * 회원가입 컨트롤러
     */
    @Operation(summary = "[테스트용] 회원가입 API", description = "관리자 회원가입")
    @PostMapping("/save")
    public String save(@RequestBody AdminDTO adminDTO) {
        adminService.save(adminDTO);
        return "성공!";
    }

    /**
     * 관리자 로그인 컨트롤러
     */
    @Operation(summary = "[관리자] 로그인 API", description = "관리자 로그인 처리하고 세션 생성")
    @PostMapping("/login")
    public ResponseEntity<BaseResponseBody<String>> login(@RequestBody LoginDTO loginDTO, HttpServletRequest request, HttpServletResponse httpServletResponse) {
        String loginId = adminService.login(loginDTO);
        // 회원이 아닌 경우
        if (loginId.equals("NO_DATA")) {
            return new ResponseEntity<BaseResponseBody<String>>(
                    new BaseResponseBody<String>(
                            HttpStatus.BAD_REQUEST.value(),
                            "회원 정보가 존재하지 않습니다.",
                            "NO_DATA"
                    ),
                    HttpStatus.BAD_REQUEST
            );
        }
        // 비밀번호가 틀린 경우
        else if (loginId.equals("WRONG_PWD")) {
            return new ResponseEntity<BaseResponseBody<String>>(
                    new BaseResponseBody<String>(
                            HttpStatus.BAD_REQUEST.value(),
                            "비밀번호가 틀렸습니다.",
                            "WRONG_PWD"
                    ),
                    HttpStatus.BAD_REQUEST
            );
        }
        // 로그인 성공한 경우
        else {
            // 세션이 있으면 존재하는 세션 반환
            // 세션이 없으면 신규 세션을 생성해서 반환
            HttpSession session = request.getSession();
            // 세션에 로그인 아이디 정보 저장
            SessionUtil.setLoginId(session, loginId);

//            ResponseCookie cookie = ResponseCookie.from("access-token", IToken);
//            ResponseCookie cookie = ResponseCookie.from("JSESSIONID", session.getId())
//                    .path("/")
//                    .sameSite("None")
//                    .httpOnly(true)
//                    .
//                    .secure(false)
//                    .build();

//            httpServletResponse.setHeader("Set-Cookie", "JSESSIONID"+"="+session.getId()+";"+"  Secure; SameSite=None");

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
     * 로그아웃 컨트롤러
     */
    @Operation(summary = "[관리자] 로그아웃 API", description = "관리자 로그아웃 처리하고 세션 소멸")
    @PostMapping("/logout")
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

    /**
     * 관리자 목록 컨트롤러
     */
    @Operation(summary = "[관리자] 관리자 목록 API", description = "사이드바 상단에 보여주는 관리자 목록")
    @GetMapping("/list")
    public ResponseEntity<BaseResponseBody<List<AdminResponseDTO>>> list(HttpSession session) {
        //String loginId = SessionUtil.getLoginId(session);
        //Optional<Admin> optionalAdmin = adminRepository.findById(loginId);
        // 올바른 관리자인 경우
        //if (optionalAdmin.isPresent()) {
            List<AdminResponseDTO> list = adminService.list();
            return new ResponseEntity<BaseResponseBody<List<AdminResponseDTO>>>(
                    new BaseResponseBody<List<AdminResponseDTO>>(
                            HttpStatus.OK.value(),
                            "성공",
                            list
                    ),
                    HttpStatus.OK
            );
//        } else {
//            return new ResponseEntity<BaseResponseBody<List<AdminResponseDTO>>>(
//                    new BaseResponseBody<List<AdminResponseDTO>>(
//                            HttpStatus.NOT_FOUND.value(),
//                            "존재하지 않는 Admin ID입니다.",
//                            null
//                    ),
//                    HttpStatus.NOT_FOUND
//            );
//        }
    }
}