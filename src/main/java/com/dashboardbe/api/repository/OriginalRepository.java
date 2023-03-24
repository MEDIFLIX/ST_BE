package com.dashboardbe.api.repository;

import com.dashboardbe.api.dto.original.OriginalContentsDTO;
import com.dashboardbe.api.dto.original.WeeklyInfoResponseDTO;
import com.dashboardbe.domain.*;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
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

    public WeeklyInfoResponseDTO selectWeeklyInfo() {
        QContentsAnalysis ca = QContentsAnalysis.contentsAnalysis;
        QMemberAnalysis ma = QMemberAnalysis.memberAnalysis;

        LocalDateTime yestDay = LocalDateTime.now().minusDays(1);
        LocalDateTime yestWeek = LocalDateTime.now().minusDays(8);

        Long totCount = jpaQueryFactory
                .select(ca.count())
                .from(ca)
                .where(ca.visitDate.between(yestWeek, yestDay),
                        (ca.category.eq(Category.ORIGINAL)))
                .fetchOne();

        Long maxCount = jpaQueryFactory
                .select(ca.contents.id.count())
                .from(ca)
                .where(ca.visitDate.between(yestWeek, yestDay)
                        , (ca.category.eq(Category.ORIGINAL)))
                .groupBy(ca.contents.id)
                .orderBy(ca.contents.id.count().desc())
                .fetchFirst();

        MedicalDepartment maxDepartment = jpaQueryFactory
                .select(ma.medicalDepartment)
                .from(ma)
                .where(ma.visitDate.between(yestWeek, yestDay))
                .groupBy(ma.medicalDepartment)
                .orderBy(ma.medicalDepartment.count().desc())
                .fetchFirst();

        String maxHospital = jpaQueryFactory
                .select(ma.hospital)
                .from(ma)
                .where(ma.visitDate.between(yestWeek, yestDay))
                .groupBy(ma.hospital)
                .orderBy(ma.hospital.count().desc())
                .fetchFirst();

        WeeklyInfoResponseDTO weeklyInfoResponseDTO = WeeklyInfoResponseDTO.builder()
                .totCount(totCount)
                .maxCount(maxCount)
                .maxDepartment(maxDepartment)
                .maxHospital(maxHospital)
                .build();

        return weeklyInfoResponseDTO;


//                            ExpressionUtils.as(
//                                JPAExpressions
//                                    .select(c.hits.max())
//                                    .from(c),
//                                    "maxCount"),
//                            ExpressionUtils.as(
//                                    JPAExpressions
//                                    .select(m.hospital.max())
//                                    .from(m),
//                                "hospital"),
//                            ExpressionUtils.as(
//                                    JPAExpressions
//                                    .select(m.medicalDepartment.count().max())
//                                    .from(m),
//                                "department"),
//                            ExpressionUtils.as(
//                                    JPAExpressions
//                                    .select(c.hits.sum())
//                                    .from(c),
//                                    "totCount")
//                        )
//
//                )
//                .from(m)
//                .leftJoin(c)
//                .on(m.id.eq(c.id.stringValue()))
//                .fetch();


    }

    public List<OriginalContentsDTO.Res> selectContentsInfo(OriginalContentsDTO.Req request) {

        QContents c = QContents.contents;

        return jpaQueryFactory
                .select(

                        Projections.fields(

                                OriginalContentsDTO.Res.class,

                                c.category.as("category"),
                                c.hits.as("hits"),
                                c.uploadDate.as("uploadDate"),
                                c.title.as("title")

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
