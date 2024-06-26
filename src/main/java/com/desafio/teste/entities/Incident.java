package com.desafio.teste.entities;

import com.desafio.teste.entities.model.BasicEntityData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table( name = "incident" )
@EntityListeners( AuditingEntityListener.class )
@JsonIgnoreProperties( { "hibernateLazyInitializer", "handler" } )
public class Incident  extends BasicEntityData {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "idIncident", nullable = false)
    private UUID idIncident;

    @NotNull
    private String name;

    @NotNull
    private String description;

    private LocalDateTime closedAt;

}
