package com.dashboardbe.api.repository;

import com.dashboardbe.api.dto.YestWeekReqDTO;
import com.dashboardbe.domain.Category;
import com.dashboardbe.domain.MemberAnalysis;
import com.dashboardbe.domain.QContentsAnalysis;
import com.dashboardbe.domain.QMemberAnalysis;
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

    public List<Category> findByIdInContentsAnalysis(YestWeekReqDTO yestWeekReqDTO) {

        QContentsAnalysis m = QContentsAnalysis.contentsAnalysis;

        return from(m)
                .select(m.category)
                .where(m.visitDate.between(yestWeekReqDTO.getYestWeek(), yestWeekReqDTO.getYestDay()))
                .fetch();
    }

}
