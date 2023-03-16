package com.dashboardbe.api.controller;

import com.dashboardbe.api.service.BoardService;
import com.dashboardbe.common.response.BaseResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/board")
public class BoardController {

    private final BoardService boardService;

    @ResponseBody
    @PostMapping(value = "save")
    public ResponseEntity<BaseResponseBody<String>> saveBoard(
            HttpServletRequest request,
            String id
    ) {

        try {

            //TODO -- 세션 로직 개발해야함

            AppSessionVo appSessionVo = AppSessionInfo.getAppSessionInfo(request);
            if (appSessionVo == null) {
                throw new IllegalStateException("세션 정보를 취득할 수 없습니다.");
            }

            String decodeMbrId = appSessionVo.getDecodeMbrId();
            if (decodeMbrId == null || decodeMbrId.isEmpty()) {
                throw new IllegalStateException("세션 정보를 취득할 수 없습니다. MbrId를 복호화 할 수 없거나 없습니다.");
            }

            boardService.save(id);

            return new ResponseEntity<BaseResponseBody<String>>(
                    new BaseResponseBody<String>(
                            HttpStatus.OK.value(),
                            "성공",
                            id
                    ),
                    HttpStatus.OK
            );
        } catch (Exception e) {

            e.printStackTrace();
            return new ResponseEntity<BaseResponseBody<String>>(
                    new BaseResponseBody<String>(
                            HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            "실패: " + e.getMessage(),
                            null
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );

        }

    }


}
