package com.desafio.teste.dtos.requests;

import com.desafio.teste.entities.Incident;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public record IncidentRequestDTO(
        @NotNull
        @NotBlank
        String name,

        @NotNull
        @NotBlank
        String description ) {

    public Incident toEntity(){
        return Incident.builder()
                .name( name )
                .description( description )
                .build();
    }
}
