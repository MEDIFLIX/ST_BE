package com.dashboardbe.api.controller;

import com.dashboardbe.aop.LoginCheck;
import com.dashboardbe.api.dto.MemberOrderDTO;
import com.dashboardbe.api.repository.AdminRepository;
import com.dashboardbe.api.service.OrderService;
import com.dashboardbe.common.SessionUtil;
import com.dashboardbe.common.response.BaseResponseBody;
import com.dashboardbe.domain.Admin;
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
public class OrderController {

    private final OrderService orderService;
    private final AdminRepository adminRepository;

    /**
     * 주간 순위 변동 get api
     */
    @GetMapping(value = "selectWeekly")
    @LoginCheck
    public ResponseEntity<BaseResponseBody<List<MemberOrderDTO>>> selectWeeklyOrder(
            HttpSession session
    ) {
        String loginId = SessionUtil.getLoginId(session);
        Optional<Admin> optionalAdmin = adminRepository.findById(loginId);
        // 올바른 관리자라면
        if (optionalAdmin.isPresent()) {

            List<MemberOrderDTO> memberOrderDTOList = orderService.selectMember();

            return new ResponseEntity<BaseResponseBody<List<MemberOrderDTO>>>(
                    new BaseResponseBody<List<MemberOrderDTO>>(
                            HttpStatus.OK.value(),
                            "성공",
                            memberOrderDTOList
                    ),
                    HttpStatus.OK
            );

        } else {
            return new ResponseEntity<BaseResponseBody<List<MemberOrderDTO>>>(
                    new BaseResponseBody<List<MemberOrderDTO>>(
                            HttpStatus.NOT_FOUND.value(),
                            "존재하지 않는 Admin ID입니다.",
                            null
                    ),
                    HttpStatus.NOT_FOUND
            );
        }
    }

}
