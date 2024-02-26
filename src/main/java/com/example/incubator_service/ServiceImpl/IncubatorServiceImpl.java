package com.example.incubator_service.ServiceImpl;

import com.example.hero_service.model.Player;
import com.example.incubator_service.DTO.EggDTO;
import com.example.incubator_service.DTO.IncubatorDTO;
import com.example.incubator_service.Exception.IncubatorFullException;
import com.example.incubator_service.Exception.NoAvailableIncubatorException;
import com.example.incubator_service.Exception.UnauthorizedOperationException;
import com.example.incubator_service.Model.Egg;
import com.example.incubator_service.Model.Incubator;
import com.example.incubator_service.Repository.EggRepository;
import com.example.incubator_service.Repository.IncubatorRepository;
import com.example.incubator_service.Service.IncubatorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@Service
public class IncubatorServiceImpl implements IncubatorService {
    private final IncubatorRepository incubatorRepository;
    private final EggRepository eggRepository;

    @Autowired
    public IncubatorServiceImpl(IncubatorRepository incubatorRepository,
                                EggRepository eggRepository) {
        this.incubatorRepository = incubatorRepository;
        this.eggRepository = eggRepository;

    }
    @Override
    public IncubatorDTO placeEggInIncubator(Long playerId, Long eggId) {
        // Retrieve player and egg information
        RestTemplate restTemplate = new RestTemplate();

        Player player = restTemplate.getForObject("http://localhost:8081/players/{playerId}", Player.class, playerId);

        Egg egg = eggRepository.findById(eggId).orElseThrow(() -> new EntityNotFoundException("Egg not found"));

        // Check if the player owns the egg
        if (!player.getPlayerId().equals(egg.getOwnerId())) {
            throw new UnauthorizedOperationException("Player does not own the specified egg");
        }

        // Find an available incubator for the player
//        Optional<Incubator> incubator = incubatorRepository.findFirstByOwnerIdAndEggsIsNotNull(playerId);
        Optional<Incubator> incubator = null;

        if (incubator == null) {
            throw new NoAvailableIncubatorException("Player does not have an available incubator");
        }

        // Check if the incubator has reached its maximum capacity
        if (incubator.get().getEggs().size() >= incubator.get().getMaxCapacity()) {
            throw new IncubatorFullException("Incubator has reached its maximum capacity");
        }


        // Add the egg to the incubator
        incubator.get().getEggs().add(egg);
        incubatorRepository.save(incubator.get());

        // Remove the egg from the player's inventory
        egg.setOwnerId(null);
        eggRepository.save(egg);

        return mapIncubatorToDTO(incubator.get());
    }

    @Override
    public List<IncubatorDTO> getIncubatorsByPlayer(Long playerId) {
        List<Incubator> incubators = incubatorRepository.findByOwnerId(playerId);
        return incubators.stream().map(this::mapIncubatorToDTO).collect(Collectors.toList());
    }

    // Other methods related to incubators

    private IncubatorDTO mapIncubatorToDTO(Incubator incubator) {
        IncubatorDTO incubatorDTO = new IncubatorDTO();
        incubatorDTO.setId(incubator.getId());
        incubatorDTO.setMaxCapacity(incubator.getMaxCapacity());
        incubatorDTO.setOwnerId(incubator.getOwnerId());
        incubatorDTO.setEggs(mapEggsToDTOs(incubator.getEggs()));
        return incubatorDTO;
    }

    private List<EggDTO> mapEggsToDTOs(List<Egg> eggs) {
        return eggs.stream().map(this::mapEggToDTO).collect(Collectors.toList());
    }

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
