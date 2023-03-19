package com.dashboardbe.api.service;

import com.dashboardbe.api.dto.ContentsOrderDTO;
import com.dashboardbe.api.dto.MemberOrderDTO;
import com.dashboardbe.api.dto.YestWeekReqDTO;
import com.dashboardbe.api.repository.OrderRepository;
import com.dashboardbe.domain.Category;
import com.dashboardbe.domain.ContentsAnalysis;
import com.dashboardbe.domain.MemberAnalysis;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<MemberOrderDTO> selectMember() {

        // 한주간 날짜 dto 에 담기
        String localDateTime = LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String yestLocalDateTime = LocalDateTime.now().minusDays(8).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String yestDay = localDateTime.substring(0, 9);
        String yestWeek = yestLocalDateTime.substring(0, 9);

        YestWeekReqDTO yestWeekReqDTO = new YestWeekReqDTO();
        yestWeekReqDTO.setYestDay(yestDay);
        yestWeekReqDTO.setYestWeek(yestWeek);

        List<MemberAnalysis> memberAnalysis = orderRepository.findByIdInMemberAnalysis(yestWeekReqDTO);

        List<MemberOrderDTO> response = new ArrayList<>();

        modelMapper.map(memberAnalysis, response);

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

        List<ContentsOrderDTO> response = new ArrayList<>();

        modelMapper.map(contentsAnalysis, response);

        return response;

    }

}
