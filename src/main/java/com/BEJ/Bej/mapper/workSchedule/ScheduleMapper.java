package com.BEJ.Bej.mapper.workSchedule;

import com.BEJ.Bej.dto.request.workRequest.ScheduleAddRequest;
import com.BEJ.Bej.dto.response.workResponse.ScheduleResponse;
import com.BEJ.Bej.entity.work.WorkSchedule;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    WorkSchedule toSchedule(ScheduleAddRequest request);

    ScheduleResponse toScheduleResponse(WorkSchedule schedule);

}
