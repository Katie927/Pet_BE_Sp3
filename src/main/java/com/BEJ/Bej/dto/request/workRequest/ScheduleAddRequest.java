package com.BEJ.Bej.dto.request.workRequest;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScheduleAddRequest {

    String userId;
    LocalDate workDate;
    String shiftId;

}
