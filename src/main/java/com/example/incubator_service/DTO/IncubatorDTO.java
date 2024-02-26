package com.example.incubator_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class IncubatorDTO {
    private Long id;
    private Long ownerId;
    private int maxCapacity;
    private List<EggDTO> eggs;
}
