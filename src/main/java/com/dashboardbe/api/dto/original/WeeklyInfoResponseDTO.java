package com.dashboardbe.api.dto.original;

import com.dashboardbe.domain.MedicalDepartment;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WeeklyInfoResponseDTO {
    private Long totCount;
    private Long maxCount;
    private MedicalDepartment maxDepartment;
    private String maxHospital;

    @Builder
    public WeeklyInfoResponseDTO(Long totCount, Long maxCount, MedicalDepartment maxDepartment, String maxHospital) {
        this.totCount = totCount;
        this.maxCount = maxCount;
        this.maxDepartment = maxDepartment;
        this.maxHospital = maxHospital;
    }
}
