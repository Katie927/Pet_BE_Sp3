package com.BEJ.Bej.repository.workSchedule;

import com.BEJ.Bej.entity.work.WorkSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<WorkSchedule, String> {
}
