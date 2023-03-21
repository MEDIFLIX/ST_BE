package com.dashboardbe.dashboardbe.order.service;

import com.dashboardbe.api.dto.MemberOrderHospitalDTO;
import com.dashboardbe.api.dto.YestWeekReqDTO;
import com.dashboardbe.api.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class OrderServiceTest {

    OrderRepository orderRepository;

    @BeforeEach
    public void setUp() {
//        MockMvcTestUtil
    }

    public void prepareOrderTest() {

        YestWeekReqDTO yestWeekReqDTO = new YestWeekReqDTO();

//        yestWeekReqDTO.setYestDay();
//        yestWeekReqDTO.setYestWeek(2023-03-13 00:00:00);

    }

    @Test
    public void selectMemberHospitalTest() {

        // given
        prepareOrderTest();

        // when
//        List<MemberOrderHospitalDTO> memberAnalysis = orderRepository.selectHospitalInMemberAnalysis(yest);

        // then


    }

}
