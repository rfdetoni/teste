package com.desafio.teste.controllers;

import com.desafio.teste.dtos.responses.IncidentResponseDTO;
import com.desafio.teste.entities.Incident;
import com.desafio.teste.exceptions.Errors;
import com.desafio.teste.exceptions.NotFoundException;
import com.desafio.teste.repositories.IncidentRepository;
import com.desafio.teste.services.IncidentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class IncidentControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IncidentService incidentService;

    @Mock
    private IncidentRepository incidentRepository;

    @Test
    @DisplayName("Should create a new incident")
    void createIncident01() throws Exception {
        String json = """
                    {
                        "name": "Test Incident",
                        "description": "This is a test incident"
                       }
                """;
        var response = mvc.perform(post("/incident").contentType("application/json").content(json)).andReturn();
        assertEquals(200, response.getResponse().getStatus());

    }

    @Test
    @DisplayName("Should not create a new incident")
    void createIncident02() throws Exception {
        String json = """
                    {
                        "name": null,
                        "description": "This is a test incident"
                       }
                """;
        var response = mvc.perform(post("/incident").contentType("application/json").content(json)).andReturn();
        assertEquals(400, response.getResponse().getStatus());

    }

    @Test
    @DisplayName("Should return a incident by id")
    void getIncident01() throws Exception {
        UUID incidentId = UUID.randomUUID();
        IncidentResponseDTO expectedResponse = IncidentResponseDTO.builder()
                .idIncident(incidentId)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .closedAt(null)
                .name("Example Incident")
                .description("This is an example incident")
                .build();

        Mockito.when(incidentService.getById(incidentId)).thenReturn(expectedResponse);

        mvc.perform(MockMvcRequestBuilders.get("/incident/{idIncident}", incidentId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }


    @Test
    @DisplayName("Should return 404")
    void getIncident02() throws Exception {
        UUID incidentId = UUID.randomUUID();

        Mockito.when(incidentService.getById(incidentId)).thenReturn(null);

        mvc.perform(MockMvcRequestBuilders.get("/incidents/{id}", incidentId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @DisplayName("Should return all incidents with a status code 200")
    void getAllIncidents01() throws Exception {
        Mockito.when(incidentService.getAll()).thenReturn(Collections.emptyList());

        mvc.perform(MockMvcRequestBuilders.get("/incident")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Should not return any incidents and a status code 404")
    void getAllIncidents02() throws Exception {
        Mockito.when(incidentService.getAll()).thenThrow(new NotFoundException(Errors.INCIDENT_NOT_FOUND));

        mvc.perform(MockMvcRequestBuilders.get("/incident")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }


    @Test
    @DisplayName("Should return 20 incidents ordered by createdAt with a status code 200")
    void getLast20Incidents01() throws Exception {
        Mockito.when(incidentService.getAll()).thenReturn(Collections.emptyList());

        mvc.perform(MockMvcRequestBuilders.get("/incident/last20")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Should update an incident")
    void updateIncident() throws Exception {
        String json = """
                    {
                        "name": "Test Incident",
                        "description": "This is a test incident"
                       }
                """;
        var response = mvc.perform(
                MockMvcRequestBuilders.put("/incident/{idIncident}", UUID.randomUUID())
                        .contentType("application/json").content(json)).andReturn().getResponse();

        assertEquals(200, response.getStatus());
    }


    @Test
    @DisplayName("Should not update an incident and return 400")
    void updateIncident02() throws Exception {
        String json = "{}";
        var response = mvc.perform(
                MockMvcRequestBuilders.put("/incident/{idIncident}", UUID.randomUUID())
                        .contentType("application/json").content(json)).andReturn().getResponse();

        assertEquals(400, response.getStatus());
    }

    @Test
    @DisplayName("Should delete an incident and return 204")
    void deleteIncident01() throws Exception {
        UUID incidentId = UUID.randomUUID();
        Mockito.when(incidentService.delete(incidentId)).thenReturn(HttpStatus.NO_CONTENT);

        var response = mvc.perform(MockMvcRequestBuilders.delete("/incident/{idIncident}", incidentId)
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertEquals(204, response.getStatus());

    }

    @Test
    @DisplayName("Should not delete an incident and return 404 if the incident is not found")
    void deleteIncident02() throws Exception {
        UUID incidentId = UUID.randomUUID();
        Mockito.when(incidentService.delete(incidentId)).thenThrow(new NotFoundException(Errors.INCIDENT_NOT_FOUND));

        var response = mvc.perform(MockMvcRequestBuilders.delete("/incident/{idIncident}", incidentId)
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertEquals(404, response.getStatus());
    }

    @Test
    @DisplayName("Should close an incident and return 200")
    void closeIncident01() throws Exception {
        UUID incidentId = UUID.randomUUID();
        Mockito.when(incidentService.close(incidentId)).thenReturn(new IncidentResponseDTO(Incident.builder().idIncident(incidentId).closedAt(LocalDateTime.now()).build()));

        var response = mvc.perform(MockMvcRequestBuilders.put("/incident/close/{idIncident}", incidentId)
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertEquals(200, response.getStatus());
    }

    @Test
    @DisplayName("Should close an incident and return 404 if the incident is not found")
    void closeIncident02() throws Exception {
        UUID incidentId = UUID.randomUUID();
        Mockito.when(incidentService.close(incidentId)).thenThrow(new NotFoundException(Errors.INCIDENT_NOT_FOUND));

        var response = mvc.perform(MockMvcRequestBuilders.put("/incident/close/{idIncident}", incidentId)
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertEquals(404, response.getStatus());
    }
}