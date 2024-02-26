package com.example.incubator_service.Controller;

import com.example.incubator_service.DTO.EggDTO;
import com.example.incubator_service.Service.EggService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eggs")
public class EggController {
    private final EggService eggService;

    @Autowired
    public EggController(EggService eggService) {
        this.eggService = eggService;
    }

    @PostMapping("/create")
    public ResponseEntity<EggDTO> createEgg(@RequestParam Long playerId, @RequestParam String eggType) {
        EggDTO createdEgg = eggService.createEgg(playerId, eggType);
        return new ResponseEntity<>(createdEgg, HttpStatus.CREATED);
    }

    @GetMapping("/player/{playerId}")
    public ResponseEntity<List<EggDTO>> getEggsByPlayer(@PathVariable Long playerId) {
        List<EggDTO> eggs = eggService.getEggsByPlayer(playerId);
        return new ResponseEntity<>(eggs, HttpStatus.OK);
    }

    // Other endpoints for managing eggs...
}
