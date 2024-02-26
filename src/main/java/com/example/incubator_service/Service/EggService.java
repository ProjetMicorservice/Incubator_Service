package com.example.incubator_service.Service;

import com.example.incubator_service.DTO.EggDTO;

import java.util.List;

public interface EggService {
    EggDTO createEgg(Long playerId, String eggType);

    List<EggDTO> getEggsByPlayer(Long playerId);
}
