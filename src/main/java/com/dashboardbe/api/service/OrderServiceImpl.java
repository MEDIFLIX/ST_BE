package com.dashboardbe.api.service;

import com.dashboardbe.api.dto.*;
import com.dashboardbe.api.repository.OrderRepository;
import com.dashboardbe.domain.Category;
import com.dashboardbe.domain.Member;
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
    public List<Long> selectMemberHospital() {

        // 한주간 날짜 dto 에 담기
        LocalDateTime localDateTime = LocalDateTime.now().minusDays(1);
        LocalDateTime yestLocalDateTime = LocalDateTime.now().minusDays(8);

        YestWeekReqDTO yestWeekReqDTO = new YestWeekReqDTO();
        yestWeekReqDTO.setYestDay(localDateTime);
        yestWeekReqDTO.setYestWeek(yestLocalDateTime);

        List<Long> memberAnalysis = orderRepository.selectHospitalInMemberAnalysis(yestWeekReqDTO);

        List<Long> hospitalSorted = memberAnalysis.stream()
                                                        .sorted()
                                                        .collect(Collectors.toList());

//        List<MemberOrderHospitalDTO> response = new ArrayList<>();
//
//        modelMapper.map(hospitalSorted, response);

        return hospitalSorted;

    }

    @Override
    public List<Long> selectMemberDepartment() {

        // 한주간 날짜 dto 에 담기
        LocalDateTime localDateTime = LocalDateTime.now().minusDays(1);
        LocalDateTime yestLocalDateTime = LocalDateTime.now().minusDays(8);

        YestWeekReqDTO yestWeekReqDTO = new YestWeekReqDTO();
        yestWeekReqDTO.setYestDay(localDateTime);
        yestWeekReqDTO.setYestWeek(yestLocalDateTime);

        List<Long> memberAnalysis = orderRepository.selectDepartmentInMemberAnalysis(yestWeekReqDTO);

        List<Long> hospitalSorted = memberAnalysis.stream()
                                                        .sorted()
                                                        .collect(Collectors.toList());

//        List<MemberOrderDepartmentDTO> response = new ArrayList<>();
//
//        modelMapper.map(hospitalSorted, response);

        return hospitalSorted;

    }

    @Override
    public List<ContentsOrderDTO> selectContent() {

        // 한주간 날짜 dto 에 담기
        LocalDateTime localDateTime = LocalDateTime.now().minusDays(1);
        LocalDateTime yestLocalDateTime = LocalDateTime.now().minusDays(8);

        YestWeekReqDTO yestWeekReqDTO = new YestWeekReqDTO();
        yestWeekReqDTO.setYestDay(localDateTime);
        yestWeekReqDTO.setYestWeek(yestLocalDateTime);

        List<Category> contentsAnalysis = orderRepository.findByIdInContentsAnalysis(yestWeekReqDTO);

        List<Category> contentsOrderSort = contentsAnalysis.stream()
                                                        .sorted()
                                                        .collect(Collectors.toList());

        List<ContentsOrderDTO> response = new ArrayList<>();

        modelMapper.map(contentsOrderSort, response);

        return response;

    }

    @Override
    public List<WeeklyVisitsDTO> selectWeeklyVisits() {

        // 한주간 날짜 dto 에 담기
        LocalDateTime localDateTime = LocalDateTime.now().minusDays(1);
        LocalDateTime yestLocalDateTime = LocalDateTime.now().minusDays(8);

        YestWeekReqDTO yestWeekReqDTO = new YestWeekReqDTO();
        yestWeekReqDTO.setYestDay(localDateTime);
        yestWeekReqDTO.setYestWeek(yestLocalDateTime);

        List<Member> contentsAnalysis = orderRepository.findWeeklyVisitsInMember(yestWeekReqDTO);

        List<Member> memberVisitsSort = contentsAnalysis.stream()
                .sorted()
                .collect(Collectors.toList());

        List<WeeklyVisitsDTO> response = new ArrayList<>();

        modelMapper.map(memberVisitsSort, response);

        return response;

    }

    @Override
    public List<Integer> selectContentChanges() {

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

        List<Integer> result = null;

        for (ContentsChangesDTO.Req req : Info) {
            result.add(req.getThisWeek() - req.getPastWeek());
        }

        return result;

    }

}
