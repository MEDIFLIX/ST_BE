package com.dashboardbe.api.controller;

import com.dashboardbe.api.dto.original.OriginalContentsDTO;
import com.dashboardbe.api.dto.original.WeeklyInfoResponseDTO;
import com.dashboardbe.api.repository.AdminRepository;
import com.dashboardbe.api.service.OriginalService;
import com.dashboardbe.common.response.BaseResponseBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/original")
@Tag(name = "Original Controller", description = "original page 컨트롤러")
public class OriginalController {

    private final AdminRepository adminRepository;
    private final OriginalService originalService;

    /**
     * 주간 정보 가져오기 GET
     */
    @Operation(summary = "[오리지널 대시보드 1번 기능] 주간 정보 조회 API", description = "영상, 진료의 주간 정보를 조회한다.")
    @GetMapping(value = "selectWeeklyInfo")
//    @LoginCheck
    public ResponseEntity<BaseResponseBody<WeeklyInfoResponseDTO>> selectWeeklyInfo(
            HttpSession session
    ) {

        WeeklyInfoResponseDTO weeklyInfo = originalService.selectWeeklyInfo();

        return new ResponseEntity<BaseResponseBody<WeeklyInfoResponseDTO>>(
                new BaseResponseBody<WeeklyInfoResponseDTO>(
                        HttpStatus.OK.value(),
                        "성공",
                        weeklyInfo

                ),
                HttpStatus.OK
        );

    }

    /**
     * 컨텐츠 검색 결과 가져오기 GET
     */
    @Operation(summary = "[테스트용] 컨텐츠 검색 결과 조회 API", description = "컨텐츠 검색 결과를 조회한다.")
    @GetMapping(value = "selectContentsInfo")
//    @LoginCheck
    public ResponseEntity<BaseResponseBody<List<OriginalContentsDTO.Res>>> selectContentsInfo(
            @RequestParam String countYn,
            @RequestParam String searchWord,
            HttpSession session
    ) {

//        String loginId = SessionUtil.getLoginId(session);
//        Optional<Admin> optionalAdmin = adminRepository.findById(loginId);
//        // 올바른 관리자라면
//        if (optionalAdmin.isPresent()) {

        OriginalContentsDTO.Req request = new OriginalContentsDTO.Req(countYn, searchWord);

        List<OriginalContentsDTO.Res> ContentsInfo = originalService.selectContentsInfo(request);

        return new ResponseEntity<BaseResponseBody<List<OriginalContentsDTO.Res>>>(
                new BaseResponseBody<List<OriginalContentsDTO.Res>>(
                        HttpStatus.OK.value(),
                        "성공",
                        ContentsInfo
                ),
                HttpStatus.OK
        );

//        } else {
//            return new ResponseEntity<BaseResponseBody<List<OriginalContentsDTO.Res>>>(
//                    new BaseResponseBody<List<OriginalContentsDTO.Res>>(
//                            HttpStatus.NOT_FOUND.value(),
//                            "존재하지 않는 Admin ID입니다.",
//                            null
//                    ),
//                    HttpStatus.NOT_FOUND
//            );
//        }

    }

}
