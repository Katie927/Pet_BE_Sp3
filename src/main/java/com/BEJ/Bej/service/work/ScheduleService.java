package com.BEJ.Bej.service.work;

import com.BEJ.Bej.dto.request.workRequest.ScheduleAddRequest;
import com.BEJ.Bej.dto.request.workRequest.ScheduleGetRequest;
import com.BEJ.Bej.dto.request.workRequest.ShiftRequest;
import com.BEJ.Bej.dto.response.workResponse.ScheduleResponse;
import com.BEJ.Bej.dto.response.workResponse.ShiftResponse;
import com.BEJ.Bej.entity.identity.User;
import com.BEJ.Bej.entity.work.Shift;
import com.BEJ.Bej.entity.work.WorkSchedule;
import com.BEJ.Bej.exception.AppException;
import com.BEJ.Bej.exception.ErrorCode;
import com.BEJ.Bej.mapper.workSchedule.ScheduleMapper;
import com.BEJ.Bej.mapper.workSchedule.ShiftMapper;
import com.BEJ.Bej.repository.UserRepository;
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
    UserRepository userRepository;

    public List<ScheduleResponse> getScheduleMonthly(ScheduleGetRequest request){
        return scheduleRepository
                .findByDateRange(request.getStartOfMonth(), request.getEndOfMonth())
                .stream().map(scheduleMapper::toScheduleResponse).toList();
    }

    public ScheduleResponse addWorkSchedule(ScheduleAddRequest request){
        log.info("Add Schedule");
        log.info(request.getUserId());
        WorkSchedule schedule = scheduleMapper.toSchedule(request);
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        schedule.setUser(user);
        Shift shift = shiftRepository.findById(request.getShiftId())
                        .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        schedule.setShift(shift);
        log.info(schedule.getUser().getFullName());
        log.info(schedule.getShift().getName());
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
