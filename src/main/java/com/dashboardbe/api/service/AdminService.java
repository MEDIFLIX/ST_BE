package com.dashboardbe.api.service;

import com.dashboardbe.api.repository.AdminRepository;
import com.dashboardbe.domain.Admin;
import com.dashboardbe.api.dto.AdminDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    /***
     * 회원 정보 저장 서비스 로직
     */
    public void save(AdminDTO adminDTO) {
        // dto -> entity 변환
        Admin admin = Admin.builder()
                .id(adminDTO.getId())
                .pwd(adminDTO.getPwd())
                .name(adminDTO.getName())
                .phoneNumber(adminDTO.getPhoneNumber())
                .profile(adminDTO.getProfile())
                .build();
        // repository의 save 메소드 호출
        adminRepository.save(admin);
    }

    /***
     * 로그인 서비스 로직
     */
    public String login(AdminDTO adminDTO) {
        /**
         * 1. 회원이 입력한 이메일로 DB에서 조회를 함
         * 2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
         */
        Optional<Admin> optionalAdmin = adminRepository.findById(adminDTO.getId());
        // 해당 이메일을 가진 회원 정보가 있는 경우
        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();
            // 비밀번호 일치
            if (admin.getPwd().equals(adminDTO.getPwd())) {
                // entity -> dto 변환 후 리턴
                return admin.getId();
            }else { // 비밀번호 불일치
                return "WRONG_PWD";
            }
        } else { // 회원이 아닌 경우
            return "NO_DATA";
        }
    }
}
