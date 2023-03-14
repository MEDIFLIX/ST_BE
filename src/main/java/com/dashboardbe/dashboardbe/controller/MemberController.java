package com.dashboardbe.dashboardbe.controller;

import com.dashboardbe.dashboardbe.dto.MemberDTO;
import com.dashboardbe.dashboardbe.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /***
     * 회원가입
     */
    @PostMapping("/member/save")
    public String save(@RequestBody MemberDTO memberDTO) {
        System.out.println("memberDTO = " + memberDTO);
        memberService.save(memberDTO);
        return "성공!";
    }

    /**
     * 로그인
     * @return null이면 로그인 실패
     */
    @PostMapping("/member/login")
    public String login(@RequestBody MemberDTO memberDTO, HttpServletRequest request) {
        MemberDTO loginResult = memberService.login(memberDTO);
        // login 성공
        if (loginResult != null) {
            // 세션이 있으면 존재하는 세션 반환
            // 세션이 없으면 신규 세션을 생성해서 반환
            HttpSession session = request.getSession();
            // 세션에 로그인 아이디 정보 저장
            System.out.println(loginResult.getMemberId());
            session.setAttribute("loginID", loginResult.getMemberId());
            System.out.println((String)session.getAttribute("loginID"));
            return session.getId();
        } else { // login 실패
            return "retry";
        }
    }

    /**
     * 로그아웃
     */
    @PostMapping("/member/logout")
    public String logout(HttpServletRequest request) {
        // false 옵션 -> 세션이 없는 경우 새로 생성하지 않도록
        HttpSession session = request.getSession(false);
        // 세션이 존재하는 경우
        if (session != null) {
            session.invalidate(); // 세션 소멸
        }
        // 로그인 화면으로 리다이렉트 시킴
        return "/member/login";
    }
}