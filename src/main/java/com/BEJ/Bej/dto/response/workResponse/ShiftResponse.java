package com.BEJ.Bej.dto.response.workResponse;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShiftResponse {

    private String name;           // "Ca sáng", "Ca chiều", "Ca tối"
    private LocalTime startTime;
    private LocalTime endTime;

}
