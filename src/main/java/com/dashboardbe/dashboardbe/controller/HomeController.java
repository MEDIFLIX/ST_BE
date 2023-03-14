package com.dashboardbe.dashboardbe.controller;

import com.dashboardbe.dashboardbe.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final TestService testService;
    // 기본 페이지 요청
    @GetMapping("/")
    public String index() {
        return "index"; // -> templates 폴더의 index.html
    }

    @GetMapping("/test")
    public String test() {
        testService.testSession();
        return "성공!";
    }
}