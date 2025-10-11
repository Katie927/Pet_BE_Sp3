package com.BEJ.Bej.mapper.workSchedule;

import com.BEJ.Bej.dto.request.workRequest.ShiftRequest;
import com.BEJ.Bej.dto.response.workResponse.ShiftResponse;
import com.BEJ.Bej.entity.work.Shift;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ShiftMapper {

    Shift toShift(ShiftRequest request);
    @Mapping(source = "id", target = "id")
    ShiftResponse toShiftResponse(Shift shift);

}
