package com.dashboardbe.api.repository;

import com.dashboardbe.api.dto.*;
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
                        /**
                         * 여기 수정해야 함 -> 병원명 추출
                         */
//                        m.hospital.count().as("hospital")
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

    public List<String> selectDepartmentInMemberAnalysis(YestWeekReqDTO yestWeekReqDTO) {

        QMemberAnalysis m = QMemberAnalysis.memberAnalysis;

        return (List<String>) jpaQueryFactory
                .select(
                        /**
                         * 여기 수정해야 함 -> 진료과명 추출
                         */
//                        m.medicalDepartment.count().as("medicalDepartment")
                )
                .from(m)
                .where(m.visitDate.between(yestWeekReqDTO.getYestWeek(), yestWeekReqDTO.getYestDay()))
                .groupBy(m.medicalDepartment)
                .orderBy(m.medicalDepartment.count().desc())
                .limit(3)
                .fetch();

    }

    public List<Long> findByIdInContentsAnalysis(YestWeekReqDTO yestWeekReqDTO) {

        QContentsAnalysis m = QContentsAnalysis.contentsAnalysis;

        return (List<Long>) jpaQueryFactory
                .select(
                        m.category.count().as("category")
                )
                .from(m)
                .where(m.visitDate.between(yestWeekReqDTO.getYestWeek(), yestWeekReqDTO.getYestDay()))
                .groupBy(m.category)
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
                .leftJoin(c)
                .on(m.id.eq(c.id.stringValue()))
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
