package com.BEJ.Bej.entity.work;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Shift {

    @Id
    private String name;           // "Ca sáng", "Ca chiều", "Ca tối"
    private LocalTime startTime;
    private LocalTime endTime;

}
