package com.dashboardbe.api.controller;

import com.dashboardbe.api.repository.ContentsRepository;
import com.dashboardbe.api.service.ContentsService;
import com.dashboardbe.common.response.BaseResponseBody;
import com.dashboardbe.domain.Contents;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*",  allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/contents")
@Tag(name = "Contents Controller", description = "동영상 콘텐츠 컨트롤러")
public class ContentsController {

    private final ContentsService contentsService;
    private final ContentsRepository contentsRepository;

    /**
     * 조회수 증가 + 콘텐츠 조회 기록 저장 컨트롤러
     */
    @Operation(summary = "[테스트용] 조회수 증가 API", description = "콘텐츠 클릭 시 조회수 증가시키고 콘텐츠 조회 기록을 저장한다.")
    @GetMapping("")
    public ResponseEntity<BaseResponseBody<Long>> addHits(@RequestParam Long contentsId) {
        Optional<Contents> optionalContents = contentsRepository.findById(contentsId);
        // 존재하는 콘텐츠라면
        if (optionalContents.isPresent()) {
            Contents contents = optionalContents.get();
            contentsService.add(contents);
            return new ResponseEntity<BaseResponseBody<Long>>(
                    new BaseResponseBody<Long>(
                            HttpStatus.OK.value(),
                            "성공",
                            contentsId
                    ),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<BaseResponseBody<Long>>(
                    new BaseResponseBody<Long>(
                            HttpStatus.NOT_FOUND.value(),
                            "존재하지 않는 콘텐츠입니다.",
                            contentsId
                    ),
                    HttpStatus.NOT_FOUND
            );
        }
    }
}
