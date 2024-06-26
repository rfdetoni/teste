package com.desafio.teste.controllers;

import com.desafio.teste.services.IncidentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class IncidentControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IncidentService incidentService;

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
    void getIncident() {
    }

    @Test
    void getAllIncidents() {
    }

    @Test
    void getLast20Incidents() {
    }

    @Test
    void updateIncident() {
    }

    @Test
    void deleteIncident() {
    }

    @Test
    void closeIncident() {
    }
}