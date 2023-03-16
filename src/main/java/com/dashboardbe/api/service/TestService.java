package com.dashboardbe.dashboardbe.service;

import com.dashboardbe.dashboardbe.aop.LoginCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {
    @LoginCheck
    public void testSession() {
        System.out.println("TestService.testSession");
    }
}
