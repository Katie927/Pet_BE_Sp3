package com.BEJ.Bej.controller.manage;

import com.BEJ.Bej.dto.request.ApiResponse;
import com.BEJ.Bej.dto.request.workRequest.ScheduleAddRequest;
import com.BEJ.Bej.dto.request.workRequest.ScheduleGetRequest;
import com.BEJ.Bej.dto.request.workRequest.ShiftRequest;
import com.BEJ.Bej.dto.response.workResponse.ScheduleResponse;
import com.BEJ.Bej.dto.response.workResponse.ShiftResponse;
import com.BEJ.Bej.service.work.ScheduleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/manage/schedule")
public class ScheduleController {

    ScheduleService scheduleService;

//    @GetMapping
//    ApiResponse<List<ScheduleResponse>> getScheduleMonthly(ScheduleGetRequest request){
//        return ApiResponse.<List<ScheduleResponse>>builder()
//                .result(scheduleService.getScheduleMonthly(request))
//                .build();
//    }
//
//    @PostMapping
//    ApiResponse<ScheduleResponse> addSchedule(ScheduleAddRequest request){
//        return ApiResponse.<ScheduleResponse>builder()
//                .result(scheduleService.add)
//                .build()
//    }

    @PostMapping("/shift")
    ApiResponse<ShiftResponse> addWorkShift(ShiftRequest request){
        return ApiResponse.<ShiftResponse>builder()
                .result(scheduleService.addWorkShift(request))
                .build();
    }

}
