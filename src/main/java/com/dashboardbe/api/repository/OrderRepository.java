package com.dashboardbe.api.repository;

import com.dashboardbe.api.dto.MemberOrderDTO;
import com.dashboardbe.api.dto.YestWeekReqDTO;
import com.dashboardbe.domain.MemberAnalysis;
import com.dashboardbe.domain.QMemberAnalysis;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class OrderRepository extends QuerydslRepositorySupport {

    public OrderRepository() {
        super(MemberAnalysis.class);
    }

    public List<MemberAnalysis> findByIdInMemberAnalysis(YestWeekReqDTO yestWeekReqDTO) {

        QMemberAnalysis m = QMemberAnalysis.memberAnalysis;

        return from(m)
                .where(m.visitDate.between(yestWeekReqDTO.getYestWeek(), yestWeekReqDTO.getYestDay()))
                .fetch();
    }

}
