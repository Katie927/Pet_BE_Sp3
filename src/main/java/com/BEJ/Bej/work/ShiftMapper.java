package com.BEJ.Bej.work;

import com.BEJ.Bej.cart.dto.request.workRequest.ShiftRequest;
import com.BEJ.Bej.cart.dto.response.workResponse.ShiftResponse;
import com.BEJ.Bej.work.entity.Shift;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ShiftMapper {

    Shift toShift(ShiftRequest request);
    @Mapping(source = "id", target = "id")
    ShiftResponse toShiftResponse(Shift shift);

}
