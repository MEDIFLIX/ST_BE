package com.dashboardbe.api.controller;

import com.dashboardbe.api.repository.ContentsRepository;
import com.dashboardbe.api.service.ContentsService;
import com.dashboardbe.common.response.BaseResponseBody;
import com.dashboardbe.domain.Contents;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ContentsController {

    private final ContentsService contentsService;
    private final ContentsRepository contentsRepository;
    @GetMapping("/contents")
    public ResponseEntity<BaseResponseBody<Long>> addHits(@RequestParam Long contentsId) {
        Optional<Contents> optionalContents = contentsRepository.findById(contentsId);
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
