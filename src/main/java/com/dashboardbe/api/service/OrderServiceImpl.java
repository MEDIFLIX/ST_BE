package com.dashboardbe.api.service;

import com.dashboardbe.api.dto.*;
import com.dashboardbe.api.repository.OrderRepository;
import com.dashboardbe.domain.Category;
import com.dashboardbe.domain.Member;
import com.dashboardbe.domain.MemberAnalysis;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<MemberOrderHospitalDTO> selectMemberHospital() {

        // 한주간 날짜 dto 에 담기
        String localDateTime = LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String yestLocalDateTime = LocalDateTime.now().minusDays(8).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String yestDay = localDateTime.substring(0, 9);
        String yestWeek = yestLocalDateTime.substring(0, 9);

        YestWeekReqDTO yestWeekReqDTO = new YestWeekReqDTO();
        yestWeekReqDTO.setYestDay(yestDay);
        yestWeekReqDTO.setYestWeek(yestWeek);

        List<MemberAnalysis> memberAnalysis = orderRepository.selectHospitalInMemberAnalysis(yestWeekReqDTO);

        List<MemberAnalysis> hospitalSorted = memberAnalysis.stream()
                                                        .sorted()
                                                        .collect(Collectors.toList());

        List<MemberOrderHospitalDTO> response = new ArrayList<>();

        modelMapper.map(hospitalSorted, response);

        return response;

    }

    @Override
    public List<MemberOrderDepartmentDTO> selectMemberDepartment() {

        // 한주간 날짜 dto 에 담기
        String localDateTime = LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String yestLocalDateTime = LocalDateTime.now().minusDays(8).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String yestDay = localDateTime.substring(0, 9);
        String yestWeek = yestLocalDateTime.substring(0, 9);

        YestWeekReqDTO yestWeekReqDTO = new YestWeekReqDTO();
        yestWeekReqDTO.setYestDay(yestDay);
        yestWeekReqDTO.setYestWeek(yestWeek);

        List<MemberAnalysis> memberAnalysis = orderRepository.selectDepartmentInMemberAnalysis(yestWeekReqDTO);

        List<MemberAnalysis> hospitalSorted = memberAnalysis.stream()
                                                        .sorted()
                                                        .collect(Collectors.toList());

        List<MemberOrderDepartmentDTO> response = new ArrayList<>();

        modelMapper.map(hospitalSorted, response);

        return response;

    }

    @Override
    public List<ContentsOrderDTO> selectContent() {

        // 한주간 날짜 dto 에 담기
        String localDateTime = LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String yestLocalDateTime = LocalDateTime.now().minusDays(8).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String yestDay = localDateTime.substring(0, 9);
        String yestWeek = yestLocalDateTime.substring(0, 9);

        YestWeekReqDTO yestWeekReqDTO = new YestWeekReqDTO();
        yestWeekReqDTO.setYestDay(yestDay);
        yestWeekReqDTO.setYestWeek(yestWeek);

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
        String localDateTime = LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String yestLocalDateTime = LocalDateTime.now().minusDays(8).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String yestDay = localDateTime.substring(0, 9);
        String yestWeek = yestLocalDateTime.substring(0, 9);

        YestWeekReqDTO yestWeekReqDTO = new YestWeekReqDTO();
        yestWeekReqDTO.setYestDay(yestDay);
        yestWeekReqDTO.setYestWeek(yestWeek);

        List<Member> contentsAnalysis = orderRepository.findWeeklyVisitsInMember(yestWeekReqDTO);

        List<Member> memberVisitsSort = contentsAnalysis.stream()
                .sorted()
                .collect(Collectors.toList());

        List<WeeklyVisitsDTO> response = new ArrayList<>();

        modelMapper.map(memberVisitsSort, response);

        return response;

    }

}
