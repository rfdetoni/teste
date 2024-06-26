package com.desafio.teste.dtos.requests;

import com.desafio.teste.entities.Incident;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
@Valid
public record IncidentRequestDTO(
        @NotNull(message = "Name is required")
        @NotBlank(message = "Name is required")
        String name,

        @NotNull(message = "Description is required")
        @NotBlank(message = "Description is required")
        String description ) {

    public Incident toEntity(){
        return Incident.builder()
                .name( name )
                .description( description )
                .build();
    }
}
