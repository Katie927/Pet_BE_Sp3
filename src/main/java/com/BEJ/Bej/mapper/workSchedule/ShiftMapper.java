package com.BEJ.Bej.mapper.workSchedule;

import com.BEJ.Bej.dto.request.workRequest.ShiftRequest;
import com.BEJ.Bej.dto.response.workResponse.ShiftResponse;
import com.BEJ.Bej.entity.work.Shift;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShiftMapper {

    Shift toShift(ShiftRequest request);
    ShiftResponse toShiftResponse(Shift shift);

}
