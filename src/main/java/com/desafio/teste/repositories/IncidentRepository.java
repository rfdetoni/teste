package com.desafio.teste.repositories;

import com.desafio.teste.entities.Incident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IncidentRepository extends JpaRepository<Incident, UUID> {

    @Query("select i from Incident i where i.idIncident = :idIncident and i.deletedAt is null")
    Optional<Incident> findById(UUID idIncident);

    @Query("select i from Incident i where i.idIncident = :idIncident and i.deletedAt is null")
    List<Incident> getAll();

    Page<Incident> findAll(Pageable pageable);
}
