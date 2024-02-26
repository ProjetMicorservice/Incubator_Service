package com.example.incubator_service.ServiceImpl;

import com.example.incubator_service.DTO.EggDTO;
import com.example.incubator_service.Model.Egg;
import com.example.incubator_service.Repository.EggRepository;
import com.example.incubator_service.Service.EggService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.stream.Collectors;

@Service
public class EggServiceImpl implements EggService {
    private final EggRepository eggRepository;

    @Autowired
    public EggServiceImpl(EggRepository eggRepository) {
        this.eggRepository = eggRepository;
    }

    @Override
    public EggDTO createEgg(Long playerId, String eggType) {
//        Player player = playerRepository.findById(playerId)
//                .orElseThrow(() -> new EntityNotFoundException("Player not found"));
        Egg egg = new Egg();
        egg.setOwnerId(playerId);
        egg.setType(eggType);
        egg.setStatus("Waiting for incubation"); // Set initial status
        Egg savedEgg = eggRepository.save(egg);
        return mapEggToDTO(savedEgg);
    }

    @Override
    public List<EggDTO> getEggsByPlayer(Long playerId) {
//        Player player = playerRepository.findById(playerId)
//                .orElseThrow(() -> new EntityNotFoundException("Player not found"));
        List<Egg> eggs = eggRepository.findByOwnerId(playerId);
        return eggs.stream().map(this::mapEggToDTO).collect(Collectors.toList());
    }

    // Other methods related to eggs...

    private EggDTO mapEggToDTO(Egg egg) {
        EggDTO eggDTO = new EggDTO();
        eggDTO.setId(egg.getId());
        eggDTO.setOwnerId(egg.getOwnerId());
        eggDTO.setType(egg.getType());
        eggDTO.setStatus(egg.getStatus());
        eggDTO.setRemainingTime(egg.getRemainingTime());
        return eggDTO;
    }
}
