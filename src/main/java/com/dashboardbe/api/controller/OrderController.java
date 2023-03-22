package com.dashboardbe.api.controller;

import com.dashboardbe.aop.LoginCheck;
import com.dashboardbe.api.dto.*;
import com.dashboardbe.api.repository.AdminRepository;
import com.dashboardbe.api.service.OrderService;
import com.dashboardbe.common.SessionUtil;
import com.dashboardbe.common.response.BaseResponseBody;
import com.dashboardbe.domain.Admin;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/order")
@Tag(name = "Order Controller", description = "주간 순위 조회 컨트롤러")
public class OrderController {

    private final OrderService orderService;
    private final AdminRepository adminRepository;

    /**
     * 주간 진료 병원 순위 get api
     */
    @Operation(summary = "[테스트용] 주간 진료 병원 순위 조회 API", description = "주간 진료 병원 순위를 조회한다.")
    @GetMapping(value = "selectWeeklyHospital")
    @LoginCheck
    public ResponseEntity<BaseResponseBody<List<Long>>> selectWeeklyHospital(
            HttpSession session
    ) {
        String loginId = SessionUtil.getLoginId(session);
        Optional<Admin> optionalAdmin = adminRepository.findById(loginId);
        // 올바른 관리자라면
        if (optionalAdmin.isPresent()) {

            List<Long> memberOrderHospitalDTOList = orderService.selectMemberHospital();

            return new ResponseEntity<BaseResponseBody<List<Long>>>(
                    new BaseResponseBody<List<Long>>(
                            HttpStatus.OK.value(),
                            "성공",
                            memberOrderHospitalDTOList
                    ),
                    HttpStatus.OK
            );

        } else {
            return new ResponseEntity<BaseResponseBody<List<Long>>>(
                    new BaseResponseBody<List<Long>>(
                            HttpStatus.NOT_FOUND.value(),
                            "존재하지 않는 Admin ID입니다.",
                            null
                    ),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    /**
     * 주간 진료 과목 순위 조회 Get api
     */
    @Operation(summary = "[테스트용] 주간 진료 과목 순위 조회 API", description = "주간 진료 과목 순위를 조회한다.")
    @GetMapping(value = "selectWeeklyDepartment")
    @LoginCheck
    public ResponseEntity<BaseResponseBody<List<Long>>> selectWeeklyDepartment(
            HttpSession session
    ) {
        String loginId = SessionUtil.getLoginId(session);
        Optional<Admin> optionalAdmin = adminRepository.findById(loginId);
        // 올바른 관리자라면
        if (optionalAdmin.isPresent()) {

            List<Long> memberOrderDepartmentDTOList = orderService.selectMemberDepartment();

            return new ResponseEntity<BaseResponseBody<List<Long>>>(
                    new BaseResponseBody<List<Long>>(
                            HttpStatus.OK.value(),
                            "성공",
                            memberOrderDepartmentDTOList
                    ),
                    HttpStatus.OK
            );

        } else {
            return new ResponseEntity<BaseResponseBody<List<Long>>>(
                    new BaseResponseBody<List<Long>>(
                            HttpStatus.NOT_FOUND.value(),
                            "존재하지 않는 Admin ID입니다.",
                            null
                    ),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    /**
     * 주간 컨텐츠 순위 변동 get api
     */
    @Operation(summary = "컨텐츠 누적 조회수 순위 조회 API", description = "1 - 3 순위까지의 컨텐츠 조회수 정보를 조회 후 제공한다.")
    @GetMapping(value = "selectWeeklyContents")
    @LoginCheck
    public ResponseEntity<BaseResponseBody<List<ContentsOrderDTO>>> selectWeeklyContents(
            HttpSession session
    ) {
        String loginId = SessionUtil.getLoginId(session);
        Optional<Admin> optionalAdmin = adminRepository.findById(loginId);
        // 올바른 관리자라면
        if (optionalAdmin.isPresent()) {

            List<ContentsOrderDTO> contentsOrderDTOList = orderService.selectContent();

            return new ResponseEntity<BaseResponseBody<List<ContentsOrderDTO>>>(
                    new BaseResponseBody<List<ContentsOrderDTO>>(
                            HttpStatus.OK.value(),
                            "성공",
                            contentsOrderDTOList
                    ),
                    HttpStatus.OK
            );

        } else {
            return new ResponseEntity<BaseResponseBody<List<ContentsOrderDTO>>>(
                    new BaseResponseBody<List<ContentsOrderDTO>>(
                            HttpStatus.NOT_FOUND.value(),
                            "존재하지 않는 Admin ID입니다.",
                            null
                    ),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    /**
     * 주간 방문자 분석 api
     */
    @Operation(summary = "주간 방문자 분석 조회 API", description = "주간 방문자 분석 정보를 조회 후 제공한다.")
    @GetMapping(value = "selectWeeklyVisits")
    @LoginCheck
    public ResponseEntity<BaseResponseBody<List<WeeklyVisitsDTO>>> selectWeeklyVisits(
            HttpSession session
    ) {
        String loginId = SessionUtil.getLoginId(session);
        Optional<Admin> optionalAdmin = adminRepository.findById(loginId);
        // 올바른 관리자라면
        if (optionalAdmin.isPresent()) {

            List<WeeklyVisitsDTO> weeklyVisitsDTOList = orderService.selectWeeklyVisits();

            return new ResponseEntity<BaseResponseBody<List<WeeklyVisitsDTO>>>(
                    new BaseResponseBody<List<WeeklyVisitsDTO>>(
                            HttpStatus.OK.value(),
                            "성공",
                            weeklyVisitsDTOList
                    ),
                    HttpStatus.OK
            );

        } else {
            return new ResponseEntity<BaseResponseBody<List<WeeklyVisitsDTO>>>(
                    new BaseResponseBody<List<WeeklyVisitsDTO>>(
                            HttpStatus.NOT_FOUND.value(),
                            "존재하지 않는 Admin ID입니다.",
                            null
                    ),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    /**
     * 주간 변동률 수치 계산 api
     */
    @Operation(summary = "변동률 조회 API", description = "주간 변동률 정보를 조회 후 제공한다.")
    @GetMapping(value = "selectContentsChanges")
    @LoginCheck
    public ResponseEntity<BaseResponseBody<List<Integer>>> selectContentsChanges(
            HttpSession session
    ) {
        String loginId = SessionUtil.getLoginId(session);
        Optional<Admin> optionalAdmin = adminRepository.findById(loginId);
        // 올바른 관리자라면
        if (optionalAdmin.isPresent()) {

            List<Integer> changes = orderService.selectContentChanges();

            return new ResponseEntity<BaseResponseBody<List<Integer>>>(
                    new BaseResponseBody<List<Integer>>(
                            HttpStatus.OK.value(),
                            "성공",
                            changes
                    ),
                    HttpStatus.OK
            );

        } else {
            return new ResponseEntity<BaseResponseBody<List<Integer>>>(
                    new BaseResponseBody<List<Integer>>(
                            HttpStatus.NOT_FOUND.value(),
                            "존재하지 않는 Admin ID입니다.",
                            null
                    ),
                    HttpStatus.NOT_FOUND
            );
        }
    }

}
