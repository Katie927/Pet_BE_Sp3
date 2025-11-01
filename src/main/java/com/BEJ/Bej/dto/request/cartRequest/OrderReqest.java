package com.BEJ.Bej.dto.request.cartRequest;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderReqest {

    String phoneNumber;
    String address;
    LocalDate updatedAt;
    LocalDate orderAt;

}
