package com.desafio.teste.dtos.responses;

import com.desafio.teste.entities.Incident;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class IncidentResponseDTO {
    private UUID idIncident;

    @JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
    private LocalDateTime updatedAt;

    @JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
    private LocalDateTime closedAt;

    private String name;
    private String description;

    public IncidentResponseDTO( Incident incident){
        this.idIncident = incident.getIdIncident();
        this.createdAt = incident.getCreatedAt();
        this.updatedAt = incident.getUpdatedAt();
        this.closedAt = incident.getClosedAt();
        this.name = incident.getName();
        this.description = incident.getDescription();
    }

}
