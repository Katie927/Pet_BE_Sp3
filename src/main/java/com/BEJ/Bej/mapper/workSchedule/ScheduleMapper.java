package com.BEJ.Bej.mapper.workSchedule;

import com.BEJ.Bej.dto.request.workRequest.ScheduleAddRequest;
import com.BEJ.Bej.dto.response.workResponse.ScheduleResponse;
import com.BEJ.Bej.entity.identity.User;
import com.BEJ.Bej.entity.work.WorkSchedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    WorkSchedule toSchedule(ScheduleAddRequest request);

    @Mapping(target = "userName", expression = "java(getUserNames(schedule.getUsers()))")
    @Mapping(target = "shiftName", source = "shift.name")
    ScheduleResponse toScheduleResponse(WorkSchedule schedule);

    default List<String> getUserNames(Set<User> users) {
        return users.stream()
                .map(User::getFullName)
                .toList();
    }
}
