package com.dashboardbe.dashboardbe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    // 기본 페이지 요청
    @GetMapping("/")
    public String index() {
        return "index"; // -> templates 폴더의 index.html
    }
}