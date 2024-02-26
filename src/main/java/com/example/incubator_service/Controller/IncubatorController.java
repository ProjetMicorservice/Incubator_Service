package com.example.incubator_service.Controller;

import com.example.incubator_service.DTO.IncubatorDTO;
import com.example.incubator_service.Service.IncubatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incubators")

public class IncubatorController {
    private final IncubatorService incubatorService;

    @Autowired
    public IncubatorController(IncubatorService incubatorService) {
        this.incubatorService = incubatorService;
    }

    @PostMapping("/{playerId}/eggs/{eggId}")
    public ResponseEntity<IncubatorDTO> placeEggInIncubator(@PathVariable Long playerId, @PathVariable Long eggId) {
        IncubatorDTO incubatorDTO = incubatorService.placeEggInIncubator(playerId, eggId);
        return new ResponseEntity<>(incubatorDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<List<IncubatorDTO>> getIncubatorsByPlayer(@PathVariable Long playerId) {
        List<IncubatorDTO> incubatorDTOs = incubatorService.getIncubatorsByPlayer(playerId);
        return new ResponseEntity<>(incubatorDTOs, HttpStatus.OK);
    }

    // Other controller methods...
}
