package com.dashboardbe.api.repository;

import com.dashboardbe.api.dto.YestWeekReqDTO;
import com.dashboardbe.domain.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
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

    public List<MemberAnalysis> selectHospitalInMemberAnalysis(YestWeekReqDTO yestWeekReqDTO) {

        QMemberAnalysis m = QMemberAnalysis.memberAnalysis;

        return (List<MemberAnalysis>) jpaQueryFactory
                .select(
                        m.hospital.count()
                )
                .from(m)
                .where(m.visitDate.between(yestWeekReqDTO.getYestWeek(), yestWeekReqDTO.getYestDay()))
                .groupBy(m.id)
                .fetchAll();

    }

    public List<MemberAnalysis> selectDepartmentInMemberAnalysis(YestWeekReqDTO yestWeekReqDTO) {

        QMemberAnalysis m = QMemberAnalysis.memberAnalysis;

        return (List<MemberAnalysis>) jpaQueryFactory
                .select(
                        m.medicalDepartment.count()
                )
                .from(m)
                .where(m.visitDate.between(yestWeekReqDTO.getYestWeek(), yestWeekReqDTO.getYestDay()))
                .groupBy(m.id)
                .fetchAll();

    }

    public List<Category> findByIdInContentsAnalysis(YestWeekReqDTO yestWeekReqDTO) {

        QContentsAnalysis m = QContentsAnalysis.contentsAnalysis;

        return (List<Category>) jpaQueryFactory
                .select(
                        m.category.count()
                )
                .from(m)
                .where(m.visitDate.between(yestWeekReqDTO.getYestWeek(), yestWeekReqDTO.getYestDay()))
                .groupBy(m.id)
                .fetchAll();

    }

    public List<Member> findWeeklyVisitsInMember(YestWeekReqDTO yestWeekReqDTO) {

        QContentsAnalysis c = QContentsAnalysis.contentsAnalysis;
        QMember m = QMember.member;

        return (List<Category>) jpaQueryFactory
                .select(
                        m.name.count(),
                        c.contents.count()
                )
                .from(m)
                .leftJoin(c)
                .on(m.id.eq(c.id.stringValue()))
                .where(m.createTime.between(yestWeekReqDTO.getYestWeek(), yestWeekReqDTO.getYestDay()))
                .fetchAll();

    }

}
