package com.example.incubator_service.Service;

import com.example.incubator_service.DTO.IncubatorDTO;

import java.util.List;

public interface IncubatorService {
    IncubatorDTO placeEggInIncubator(Long playerId, Long eggId);

    List<IncubatorDTO> getIncubatorsByPlayer(Long playerId);
}
