package com.example.incubator_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class EggDTO {
    private Long id;
    private Long ownerId;
    private String type;
    private String status;
    private Long remainingTime;
}
