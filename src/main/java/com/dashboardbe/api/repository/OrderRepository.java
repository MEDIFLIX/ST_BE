package com.dashboardbe.api.repository;

import com.dashboardbe.api.dto.ContentsChangesDTO;
import com.dashboardbe.api.dto.MemberOrderDepartmentDTO;
import com.dashboardbe.api.dto.MemberOrderHospitalDTO;
import com.dashboardbe.api.dto.YestWeekReqDTO;
import com.dashboardbe.domain.*;
import com.querydsl.jpa.JPAExpressions;
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

    public List<MemberOrderHospitalDTO> selectHospitalInMemberAnalysis(YestWeekReqDTO yestWeekReqDTO) {

        QMemberAnalysis m = QMemberAnalysis.memberAnalysis;

        return (List<MemberOrderHospitalDTO>) jpaQueryFactory
                .select(
                        m.hospital.count()
                )
                .from(m)
                .where(m.visitDate.between(yestWeekReqDTO.getYestWeek(), yestWeekReqDTO.getYestDay()))
                .groupBy(m.id)
                .fetchAll();

    }

    public List<MemberOrderDepartmentDTO> selectDepartmentInMemberAnalysis(YestWeekReqDTO yestWeekReqDTO) {

        QMemberAnalysis m = QMemberAnalysis.memberAnalysis;

        return (List<MemberOrderDepartmentDTO>) jpaQueryFactory
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

        return (List<Member>) jpaQueryFactory
                .select(

                        JPAExpressions
                                .select(c.contents.count())
                                .from(c)
                                .where(c.visitDate.between(yestWeekReqDTO.getYestWeek(), yestWeekReqDTO.getYestDay())),

                        JPAExpressions
                                .select(m.name.count())
                                .from(m)
                                .where(m.createTime.between(yestWeekReqDTO.getYestWeek(), yestWeekReqDTO.getYestDay())),

                        JPAExpressions
                                .select(m.name.count())
                                .from(m)
                                .where(
                                        m.createTime.between(yestWeekReqDTO.getYestWeek(), yestWeekReqDTO.getYestDay()),
                                        m.isMember.eq("N")
                                ),

                        JPAExpressions
                                .select(m.name.count())
                                .from(m)
                                .where(
                                        m.createTime.between(yestWeekReqDTO.getYestWeek(), yestWeekReqDTO.getYestDay()),

                                )

                )
                .from(m)
                .leftJoin(c)
                .on(m.id.eq(c.id.stringValue()))
                .fetchAll();

    }

    public List<ContentsChangesDTO.Req> findYestContentChanges(YestWeekReqDTO yestWeekReqDTO) {

        QContentsAnalysis m = QContentsAnalysis.contentsAnalysis;

        return (List<ContentsChangesDTO.Req>) jpaQueryFactory
                .select(
                        JPAExpressions
                                .select(m.category.count())
                                .from(m)
                                .where(m.visitDate.between(yestWeekReqDTO.getYestWeek(), yestWeekReqDTO.getYestDay())),
                        JPAExpressions
                                .select(m.category.count())
                                .from(m)
                                .where(m.visitDate.between(yestWeekReqDTO.getPastWeek(), yestWeekReqDTO.getYestWeek()))
                )
                .from(m)
                .groupBy(m.id)
                .fetchAll();

    }

}
