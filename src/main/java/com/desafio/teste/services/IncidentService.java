package com.desafio.teste.services;

import com.desafio.teste.dtos.requests.IncidentRequestDTO;
import com.desafio.teste.dtos.responses.IncidentResponseDTO;
import com.desafio.teste.repositories.IncidentRepository;
import com.desafio.teste.exceptions.Errors;
import com.desafio.teste.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Validated
public class IncidentService {

    private final IncidentRepository incidentRepository;

    public IncidentResponseDTO create(IncidentRequestDTO dto){
        var incident = dto.toEntity();
        incidentRepository.save(incident);
        return new IncidentResponseDTO(incident);
    }

    public HttpStatus update(UUID incidentId, IncidentRequestDTO dto){
        var dbIncident = incidentRepository.findById( incidentId ).orElseThrow( () -> new NotFoundException(Errors.INCIDENT_NOT_FOUND));
        dbIncident.setDescription( dto.description() );
        dbIncident.setName( dto.name() );
        incidentRepository.save(dbIncident);
        return HttpStatus.OK;
    }

    public IncidentResponseDTO close(UUID incidentId){
        var dbIncident = incidentRepository.findById( incidentId ).orElseThrow( () -> new NotFoundException( Errors.INCIDENT_NOT_FOUND) );
        dbIncident.setClosedAt( LocalDateTime.now() );
        return new IncidentResponseDTO( incidentRepository.save( dbIncident ) );
    }

    public HttpStatus delete(UUID incidentId){
        var dbIncident = incidentRepository.findById( incidentId ).orElseThrow( () -> new NotFoundException( Errors.INCIDENT_NOT_FOUND) );
        dbIncident.setDeletedAt( LocalDateTime.now() );

        incidentRepository.save( dbIncident );
        return HttpStatus.NO_CONTENT;
    }

    public List<IncidentResponseDTO> getAll(){
        var incidents = incidentRepository.getAll();
        return incidents.stream().map(IncidentResponseDTO::new).toList();
    }

    public Page<IncidentResponseDTO> getAllPaged(int page, int size){
        Pageable pageable = PageRequest.of( page, size, Sort.by("createdAt").descending());
        var incidents = incidentRepository.findAll(pageable );
        return incidents.map(IncidentResponseDTO::new);
    }

    public IncidentResponseDTO getById( UUID idIncident){
        var incident = incidentRepository.findById( idIncident ).orElseThrow( () -> new NotFoundException(Errors.INCIDENT_NOT_FOUND) );
        return new IncidentResponseDTO( incident );
    }

}
