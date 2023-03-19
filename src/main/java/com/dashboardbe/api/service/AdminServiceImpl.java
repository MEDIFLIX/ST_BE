package com.dashboardbe.api.service;

import com.dashboardbe.aop.LoginCheck;
import com.dashboardbe.api.dto.AdminDTO;
import com.dashboardbe.api.dto.AdminResponseDTO;
import com.dashboardbe.api.dto.LoginDTO;
import com.dashboardbe.api.repository.AdminRepository;
import com.dashboardbe.domain.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final AdminRepository adminRepository;

    /***
     * 회원 정보 저장 서비스 로직
     */
    @Override
    public void save(AdminDTO adminDTO) {
        // dto -> entity 변환
        Admin admin = Admin.builder()
                .id(adminDTO.getId())
                .pwd(adminDTO.getPwd())
                .name(adminDTO.getName())
                .phoneNumber(adminDTO.getPhoneNumber())
                .role(adminDTO.getRole())
                .build();
        // repository의 save 메소드 호출
        adminRepository.save(admin);
    }

    /***
     * 로그인 서비스 로직
     */
    @Override
    public String login(LoginDTO loginDTO) {
        /**
         * 1. 회원이 입력한 이메일로 DB에서 조회를 함
         * 2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
         */
        Optional<Admin> optionalAdmin = adminRepository.findById(loginDTO.getId());
        // 해당 이메일을 가진 회원 정보가 있는 경우
        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();
            // 비밀번호 일치
            if (admin.getPwd().equals(loginDTO.getPwd())) {
                // entity -> dto 변환 후 리턴
                return admin.getId();
            }else { // 비밀번호 불일치
                return "WRONG_PWD";
            }
        } else { // 회원이 아닌 경우
            return "NO_DATA";
        }
    }

    /**
     * 관리자 목록 페이지네이션 로직
     */
    @Override
    @LoginCheck
    public List<AdminResponseDTO> list() {
        List<AdminResponseDTO> list = new ArrayList<>();
        List<Admin> adminList = adminRepository.findAll();
        for (Admin admin : adminList) {
            AdminResponseDTO adminResponseDTO = AdminResponseDTO.builder()
                    .adminId(admin.getId())
                    .name(admin.getName())
                    .phoneNumber(admin.getPhoneNumber())
                    .role(admin.getRole())
                    .build();
            list.add(adminResponseDTO);
        }
        return list;
    }
}
