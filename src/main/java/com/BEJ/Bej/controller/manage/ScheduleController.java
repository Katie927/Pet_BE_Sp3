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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/manage/schedule")
public class ScheduleController {

    ScheduleService scheduleService;

    @PostMapping("/monthly")
    ApiResponse<List<ScheduleResponse>> getScheduleMonthly(@RequestBody ScheduleGetRequest request){
        return ApiResponse.<List<ScheduleResponse>>builder()
                .result(scheduleService.getScheduleMonthly(request))
                .build();
    }

    @PostMapping
    ApiResponse<ScheduleResponse> addSchedule(@RequestBody ScheduleAddRequest request){
        return ApiResponse.<ScheduleResponse>builder()
                .result(scheduleService.addWorkSchedule(request))
                .build();
    }

    @PostMapping("/shift")
    ApiResponse<ShiftResponse> addWorkShift(@RequestBody ShiftRequest request){
        return ApiResponse.<ShiftResponse>builder()
                .result(scheduleService.addWorkShift(request))
                .build();
    }

    @GetMapping("/shifts")
    ApiResponse<List<ShiftResponse>> getWorkShift(){
        return ApiResponse.<List<ShiftResponse>>builder()
                .result(scheduleService.getWorkShift())
                .build();
    }

}
