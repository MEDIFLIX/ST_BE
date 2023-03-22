package com.dashboardbe.api.repository;

import com.dashboardbe.api.dto.ContentsChangesDTO;
import com.dashboardbe.api.dto.MemberOrderHospitalDTO;
import com.dashboardbe.api.dto.YestWeekReqDTO;
import com.dashboardbe.api.dto.original.OriginalContentsDTO;
import com.dashboardbe.api.dto.original.OriginalWeeklyInfoDTO;
import com.dashboardbe.domain.MemberAnalysis;
import com.dashboardbe.domain.QContents;
import com.dashboardbe.domain.QMember;
import com.dashboardbe.domain.QMemberAnalysis;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class OriginalRepository extends QuerydslRepositorySupport {

    private final EntityManager entityManager;
    private final JPAQueryFactory jpaQueryFactory;

    public OriginalRepository(EntityManager entityManager, JPAQueryFactory jpaQueryFactory) {
        super(MemberAnalysis.class);
        this.entityManager = entityManager;
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<OriginalWeeklyInfoDTO.Res> selectWeeklyInfo () {

        QContents c = QContents.contents;
        QMember m = QMember.member;

        return (List<OriginalWeeklyInfoDTO.Res>) jpaQueryFactory
                .select(

                        Projections.fields(

                                OriginalWeeklyInfoDTO.Res.class,

                            JPAExpressions
                                    .select(c.hits.max())
                                    .from(c),
                            JPAExpressions
                                    .select(m.hospital.max())
                                    .from(m),
                            JPAExpressions
                                    .select(m.medicalDepartment.max())
                                    .from(m),
                            JPAExpressions
                                    .select(c.hits.sum())
                                    .from(c)
                        )

                )
                .fetch();


    }

    public List<OriginalContentsDTO.Res> selectContentsInfo (OriginalContentsDTO.Req request) {

    QContents c = QContents.contents;

        return jpaQueryFactory
                .select(

                        Projections.fields(

                                OriginalContentsDTO.Res.class,

                                c.category,
                                c.hits,
                                c.uploadDate,
                                c.title

                        )

                )
                .from(c)
                .where(
                        c.title.eq(request.getSearchWord()),
                        c.countYn.eq("Y")
                )
                .fetch();

    }

}
