package com.dashboardbe.api.service;

import com.dashboardbe.api.dto.ContentsChangesDTO;
import com.dashboardbe.api.dto.ContentsOrderDTO;
import com.dashboardbe.api.dto.WeeklyVisitsDTO;
import com.dashboardbe.api.dto.YestWeekReqDTO;
import com.dashboardbe.api.repository.OrderRepository;
import com.dashboardbe.domain.MedicalDepartment;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<String> selectMemberHospital() {

        // 한주간 날짜 dto 에 담기
        LocalDateTime localDateTime = LocalDateTime.now().minusDays(1);
        LocalDateTime yestLocalDateTime = LocalDateTime.now().minusDays(8);

        YestWeekReqDTO yestWeekReqDTO = new YestWeekReqDTO();
        yestWeekReqDTO.setYestDay(localDateTime);
        yestWeekReqDTO.setYestWeek(yestLocalDateTime);

        List<String> memberAnalysis = orderRepository.selectHospitalInMemberAnalysis(yestWeekReqDTO);

//        List<Long> hospitalSorted = memberAnalysis.stream()
//                                                        .sorted()
//                                                        .collect(Collectors.toList());

//        List<MemberOrderHospitalDTO> response = new ArrayList<>();
//
//        modelMapper.map(hospitalSorted, response);

        return memberAnalysis;

    }

    @Override
    public List<MedicalDepartment> selectMemberDepartment() {

        // 한주간 날짜 dto 에 담기
        LocalDateTime localDateTime = LocalDateTime.now().minusDays(1);
        LocalDateTime yestLocalDateTime = LocalDateTime.now().minusDays(8);

        YestWeekReqDTO yestWeekReqDTO = new YestWeekReqDTO();
        yestWeekReqDTO.setYestDay(localDateTime);
        yestWeekReqDTO.setYestWeek(yestLocalDateTime);

        List<MedicalDepartment> memberAnalysis = orderRepository.selectDepartmentInMemberAnalysis(yestWeekReqDTO);

//        List<Long> hospitalSorted = memberAnalysis.stream()
//                                                        .sorted()
//                                                        .collect(Collectors.toList());

//        List<MemberOrderDepartmentDTO> response = new ArrayList<>();
//
//        modelMapper.map(hospitalSorted, response);

        return memberAnalysis;

    }

    @Override
    public List<ContentsOrderDTO> selectContent() {

        // 한주간 날짜 dto 에 담기
        LocalDateTime localDateTime = LocalDateTime.now().minusDays(1);
        LocalDateTime yestLocalDateTime = LocalDateTime.now().minusDays(8);

        YestWeekReqDTO yestWeekReqDTO = new YestWeekReqDTO();
        yestWeekReqDTO.setYestDay(localDateTime);
        yestWeekReqDTO.setYestWeek(yestLocalDateTime);

        List<ContentsOrderDTO> contentsAnalysis = orderRepository.findByIdInContentsAnalysis(yestWeekReqDTO);

//        List<Tuple> contentsOrderSort = contentsAnalysis.stream()
//                                                        .sorted()
//                                                        .collect(Collectors.toList());

//        List<ContentsOrderDTO> response = new ArrayList<>();
//
//        modelMapper.map(contentsOrderSort, response);

        return contentsAnalysis;

    }

    @Override
    public List<WeeklyVisitsDTO> selectWeeklyVisits() {

        // 한주간 날짜 dto 에 담기
        LocalDateTime localDateTime = LocalDateTime.now().minusDays(1);
        LocalDateTime yestLocalDateTime = LocalDateTime.now().minusDays(8);

        YestWeekReqDTO yestWeekReqDTO = new YestWeekReqDTO();
        yestWeekReqDTO.setYestDay(localDateTime);
        yestWeekReqDTO.setYestWeek(yestLocalDateTime);

        List<WeeklyVisitsDTO> contentsAnalysis = orderRepository.findWeeklyVisitsInMember(yestWeekReqDTO);

        List<WeeklyVisitsDTO> memberVisitsSort = contentsAnalysis.stream()
                .sorted()
                .collect(Collectors.toList());

        List<WeeklyVisitsDTO> response = new ArrayList<>();

        modelMapper.map(memberVisitsSort, response);

        return response;

    }

    @Override
    public List<Long> selectContentChanges() {

        // 이번 한주간 날짜 dto 에 담기
        LocalDateTime localDateTime = LocalDateTime.now().minusDays(1);
        LocalDateTime yestLocalDateTime = LocalDateTime.now().minusDays(8);

        // 지난 한주간 날짜 dto 에 담기
        LocalDateTime pastLocalDateTime = LocalDateTime.now().minusDays(15);

        YestWeekReqDTO yestWeekReqDTO = new YestWeekReqDTO();
        yestWeekReqDTO.setYestDay(localDateTime);
        yestWeekReqDTO.setYestWeek(yestLocalDateTime);
        yestWeekReqDTO.setPastWeek(pastLocalDateTime);

        List<ContentsChangesDTO.Req> Info = orderRepository.findYestContentChanges(yestWeekReqDTO);

        List<Long> result = null;

        for (ContentsChangesDTO.Req req : Info) {
            result.add(req.getThisWeek() - req.getPastWeek());
        }

        return result;

    }

}
