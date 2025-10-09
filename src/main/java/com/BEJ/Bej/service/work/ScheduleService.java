package com.BEJ.Bej.service.work;

import com.BEJ.Bej.dto.request.workRequest.ScheduleAddRequest;
import com.BEJ.Bej.dto.request.workRequest.ScheduleGetRequest;
import com.BEJ.Bej.dto.request.workRequest.ShiftRequest;
import com.BEJ.Bej.dto.response.workResponse.ScheduleResponse;
import com.BEJ.Bej.dto.response.workResponse.ShiftResponse;
import com.BEJ.Bej.entity.work.Shift;
import com.BEJ.Bej.entity.work.WorkSchedule;
import com.BEJ.Bej.mapper.workSchedule.ScheduleMapper;
import com.BEJ.Bej.mapper.workSchedule.ShiftMapper;
import com.BEJ.Bej.repository.workSchedule.ScheduleRepository;
import com.BEJ.Bej.repository.workSchedule.ShiftRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ScheduleService {

    ShiftMapper shiftMapper;
    ShiftRepository shiftRepository;
    ScheduleMapper scheduleMapper;
    ScheduleRepository scheduleRepository;

//    public List<ScheduleResponse> getScheduleMonthly(ScheduleGetRequest request){
//        return
//    }
//
    public ScheduleResponse addWorkSchedule(ScheduleAddRequest request){
        log.info("Add Schedule");
        WorkSchedule schedule = scheduleMapper.toSchedule(request);
        return scheduleMapper.toScheduleResponse(scheduleRepository.save(schedule));
    }

    public ShiftResponse addWorkShift(ShiftRequest request){
//        log.info(String.valueOf(request.getStartTime()));
        Shift shift = shiftMapper.toShift(request);
        return shiftMapper.toShiftResponse(shiftRepository.save(shift));
    }

    public List<ShiftResponse> getWorkShift(){
        return shiftRepository.findAll().stream().map(shiftMapper::toShiftResponse).toList();
    }

}
