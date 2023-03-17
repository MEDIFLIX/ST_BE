package com.dashboardbe.batch.repository;

import com.dashboardbe.batch.dto.ContentsDTO;
import com.dashboardbe.domain.Contents;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DailyPopularModelMapper {

    DailyPopularModelMapper INSTANCE = Mappers.getMapper(DailyPopularModelMapper.class);

    ContentsDTO toDTO(Contents contents, String id);
}
