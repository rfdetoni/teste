package com.desafio.teste.controllers;

import com.desafio.teste.IncidentService;
import com.desafio.teste.dtos.requests.IncidentRequestDTO;
import com.desafio.teste.dtos.responses.IncidentResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Tag(name = "Incidents", description = "Operations about incidents")
@RestController
@RequestMapping(value = "/incident", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class IncidentController {

    private final IncidentService incidentService;

    @PostMapping()
    public IncidentResponseDTO createIncident(@Valid @RequestBody IncidentRequestDTO incident){
        return incidentService.create(incident);
    }

    @GetMapping("/{idIncident}")
    public IncidentResponseDTO getIncident(@PathVariable("idIncident")  UUID idIncident){
        return incidentService.getById( idIncident );
    }

    @GetMapping()
    public List<IncidentResponseDTO> getAllIncidents(){
        return incidentService.getAll();
    }

    @GetMapping("/last20")
    public Page<IncidentResponseDTO> getLast20Incidents(){
        return incidentService.getAllPaged(0,20);
    }

    @PutMapping("/{idIncident}")
    public HttpStatus updateIncident(@PathVariable("idIncident")  UUID idIncident,
                                     @Valid @RequestBody IncidentRequestDTO incident){
        return incidentService.update(idIncident, incident);
    }

    @DeleteMapping("/{idIncident}")
    public HttpStatus deleteIncident(@PathVariable("idIncident")  UUID idIncident){
        return incidentService.delete( idIncident );
    }

    @PutMapping("/close/{idIncident}")
    public IncidentResponseDTO closeIncident(@PathVariable("idIncident")  UUID idIncident){
        return incidentService.close( idIncident );
    }
}
