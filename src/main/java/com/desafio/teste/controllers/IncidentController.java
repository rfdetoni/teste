package com.desafio.teste.controllers;

import com.desafio.teste.services.IncidentService;
import com.desafio.teste.dtos.requests.IncidentRequestDTO;
import com.desafio.teste.dtos.responses.IncidentResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Incidents", description = "Operations about incidents")
@RestController
@RequestMapping(value = "/incident", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class IncidentController {

    private final IncidentService incidentService;

    @Operation(
            summary = "Create a new incident",
            description = "Create a new incident, the response is a object with the incident information",
            tags = {"Post"})
    @PostMapping
    public IncidentResponseDTO createIncident(@Valid @RequestBody IncidentRequestDTO incident){
        return incidentService.create(incident);
    }

    @Operation(
            summary = "Get by id",
            description = "Get a incident by id",
            tags = {"Get"})
    @GetMapping("/{idIncident}")
    public IncidentResponseDTO getIncident(@PathVariable("idIncident")  UUID idIncident){
        return incidentService.getById( idIncident );
    }

    @Operation(
            summary = "Get all",
            description = "Get a list of all incidents that are not deleted",
            tags = {"Get"})
    @GetMapping()
    public List<IncidentResponseDTO> getAllIncidents(){
        return incidentService.getAll();
    }

    @Operation(
            summary = "Get the last 20",
            description = "Get a list of the last 20 incidents that are not deleted, ordered by its creation date, descending",
            tags = {"Get"})
    @GetMapping("/last20")
    public Page<IncidentResponseDTO> getLast20Incidents(){
        return incidentService.getAllPaged(0,20);
    }

    @Operation(
            summary = "Update",
            description = "Update a incident, alter is name and description",
            tags = {"Update"})
    @PutMapping("/{idIncident}")
    public HttpStatus updateIncident(@PathVariable("idIncident")  UUID idIncident,
                                     @Valid @RequestBody IncidentRequestDTO incident){
        return incidentService.update(idIncident, incident);
    }


    @Operation(
            summary = "Delete",
            description = "Delete logically a incident, it is not physically deleted",
            tags = {"Delete"})
    @DeleteMapping("/{idIncident}")
    public HttpStatus deleteIncident(@PathVariable("idIncident")  UUID idIncident){
        return incidentService.delete( idIncident );
    }

    @Operation(
            summary = "Close",
            description = "Close a incident, the property closedAt is set to the current date and time",
            tags = {"Close"})
    @PutMapping("/close/{idIncident}")
    public IncidentResponseDTO closeIncident(@PathVariable("idIncident")  UUID idIncident){
        return incidentService.close( idIncident );
    }
}
