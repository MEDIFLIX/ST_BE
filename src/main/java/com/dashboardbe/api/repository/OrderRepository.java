package com.dashboardbe.api.repository;

import com.dashboardbe.api.dto.*;
import com.dashboardbe.domain.*;
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

    public List<Long> selectHospitalInMemberAnalysis(YestWeekReqDTO yestWeekReqDTO) {

        QMemberAnalysis m = QMemberAnalysis.memberAnalysis;

        return jpaQueryFactory
                .select(
                        m.hospital.count().as("hospital")
                )
                .from(m)
                .where(m.visitDate.between(yestWeekReqDTO.getYestWeek(), yestWeekReqDTO.getYestDay()))
                .groupBy(m.id)
                .fetch();

    }

    public List<Long> selectDepartmentInMemberAnalysis(YestWeekReqDTO yestWeekReqDTO) {

        QMemberAnalysis m = QMemberAnalysis.memberAnalysis;

        return (List<Long>) jpaQueryFactory
                .select(
                        m.medicalDepartment.count().as("medicalDepartment")
                )
                .from(m)
                .where(m.visitDate.between(yestWeekReqDTO.getYestWeek(), yestWeekReqDTO.getYestDay()))
                .groupBy(m.id)
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
                .groupBy(m.id)
                .fetch();

    }

    public List<WeeklyVisitsDTO> findWeeklyVisitsInMember(YestWeekReqDTO yestWeekReqDTO) {

        QContentsAnalysis c = QContentsAnalysis.contentsAnalysis;
        QMember m = QMember.member;

        return jpaQueryFactory
                .select(

                        Projections.fields(

                                WeeklyVisitsDTO.class,

                            JPAExpressions
                                    .select(c.contents.count().as("ContentsHits"))
                                    .from(c)
                                    .where(c.visitDate.between(yestWeekReqDTO.getYestWeek(), yestWeekReqDTO.getYestDay())),

                            JPAExpressions
                                    .select(m.name.count().as("all"))
                                    .from(m)
                                    .where(m.createTime.between(yestWeekReqDTO.getYestWeek(), yestWeekReqDTO.getYestDay())),

                            JPAExpressions
                                    .select(m.name.count().as("quit"))
                                    .from(m)
                                    .where(
                                            m.createTime.between(yestWeekReqDTO.getYestWeek(), yestWeekReqDTO.getYestDay()),
                                            m.isMember.eq("N")
                                    ),

                            JPAExpressions
                                    .select(m.name.count().as("newbie"))
                                    .from(m)
                                    .where(
                                            m.createTime.between(LocalDateTime.now().minusDays(2), LocalDateTime.now().minusDays(1))
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

                            JPAExpressions
                                    .select(m.category.count().as("thisWeek"))
                                    .from(m)
                                    .where(m.visitDate.between(yestWeekReqDTO.getYestWeek(), yestWeekReqDTO.getYestDay())),
                            JPAExpressions
                                    .select(m.category.count().as("pastWeek"))
                                    .from(m)
                                    .where(m.visitDate.between(yestWeekReqDTO.getPastWeek(), yestWeekReqDTO.getYestWeek()))

                        )

                )
                .from(m)
                .groupBy(m.id)
                .fetch();

    }

}
