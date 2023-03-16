package com.dashboardbe.api.service;

import com.dashboardbe.aop.LoginCheck;
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
