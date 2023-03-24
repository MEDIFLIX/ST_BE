package com.dashboardbe.api.repository;

import com.dashboardbe.api.dto.ContentsChangesDTO;
import com.dashboardbe.api.dto.ContentsOrderDTO;
import com.dashboardbe.api.dto.WeeklyVisitsDTO;
import com.dashboardbe.api.dto.YestWeekReqDTO;
import com.dashboardbe.domain.*;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class OrderRepository extends QuerydslRepositorySupport {

    private final EntityManager entityManager;
    private final JPAQueryFactory jpaQueryFactory;

    public OrderRepository(EntityManager entityManager, JPAQueryFactory jpaQueryFactory) {
        super(MemberAnalysis.class);
        this.entityManager = entityManager;
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<String> selectHospitalInMemberAnalysis(YestWeekReqDTO yestWeekReqDTO) {

        QMemberAnalysis m = QMemberAnalysis.memberAnalysis;

        return jpaQueryFactory
                .select(
                        m.hospital.as("hospital")
                )
                .from(m)
                .where(
                        m.visitDate.between(yestWeekReqDTO.getYestWeek(), yestWeekReqDTO.getYestDay())
                )
                .groupBy(m.hospital)
                .orderBy(m.hospital.count().desc())
                .limit(3)
                .fetch();

    }

    public List<MedicalDepartment> selectDepartmentInMemberAnalysis(YestWeekReqDTO yestWeekReqDTO) {

        QMemberAnalysis m = QMemberAnalysis.memberAnalysis;

        return jpaQueryFactory
                .select(
                        m.medicalDepartment.as("medicalDepartment")
                )
                .from(m)
                .where(m.visitDate.between(yestWeekReqDTO.getYestWeek(), yestWeekReqDTO.getYestDay()))
                .groupBy(m.medicalDepartment)
                .orderBy(m.medicalDepartment.count().desc())
                .limit(3)
                .fetch();

    }

    public List<ContentsOrderDTO> findByIdInContentsAnalysis(YestWeekReqDTO yestWeekReqDTO) {

        QContentsAnalysis m = QContentsAnalysis.contentsAnalysis;

        return jpaQueryFactory
                .select(
                        Projections.fields(
                                ContentsOrderDTO.class,
                                m.category.as("category"),
                                m.category.count().as("count"))
                )
                .from(m)
                .where(m.visitDate.between(yestWeekReqDTO.getYestWeek(), yestWeekReqDTO.getYestDay()))
                .groupBy(m.category)
                .orderBy(m.category.count().desc())
                .limit(3)
                .fetch();

    }

    public List<WeeklyVisitsDTO> findWeeklyVisitsInMember(YestWeekReqDTO yestWeekReqDTO) {

        QContentsAnalysis c = QContentsAnalysis.contentsAnalysis;
        QMember m = QMember.member;

        return jpaQueryFactory
                .select(

                        Projections.fields(

                                WeeklyVisitsDTO.class,

                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(c.contents.count())
                                                .from(c)
                                                .where(c.visitDate.between(yestWeekReqDTO.getYestWeek(), yestWeekReqDTO.getYestDay())),
                                        "ContentsHits"
                                ),

                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(m.name.count())
                                                .from(m)
                                                .where(m.createTime.between(yestWeekReqDTO.getYestWeek(), yestWeekReqDTO.getYestDay())),
                                        "all"
                                ),

                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(m.name.count())
                                                .from(m)
                                                .where(
                                                        m.createTime.between(yestWeekReqDTO.getYestWeek(), yestWeekReqDTO.getYestDay()),
                                                        m.isMember.eq("N")
                                                ),
                                        "quit"
                                ),

                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(m.name.count())
                                                .from(m)
                                                .where(
                                                        m.createTime.between(LocalDateTime.now().minusDays(2), LocalDateTime.now().minusDays(1))
                                                ),
                                        "newbie"
                                )

                        )

                )
                .from(m)
                .fetch();

    }

    public List<ContentsChangesDTO.Req> findYestContentChanges(YestWeekReqDTO yestWeekReqDTO) {

        QContentsAnalysis m = QContentsAnalysis.contentsAnalysis;

        return jpaQueryFactory
                .select(

                        Projections.fields(

                                ContentsChangesDTO.Req.class,

//                                Math.toIntExact(
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(m.category.count())
                                                .from(m)
                                                .where(m.visitDate.between(yestWeekReqDTO.getYestWeek(), yestWeekReqDTO.getYestDay())),
                                        "thisWeek"),
//                                ),
//                                Math.toIntExact(
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(m.category.count())
                                                .from(m)
                                                .where(m.visitDate.between(yestWeekReqDTO.getPastWeek(), yestWeekReqDTO.getYestWeek())),
                                        "pastWeek")
//                                )

                        )

                )
                .from(m)
                .fetch();

    }

}
