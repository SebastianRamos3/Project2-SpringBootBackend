// package com.javatechie.spring.mokito.api;

// import org.junit.runner.RunWith;

// @RunWith(SpringRunner.class)
// @SpringBootTest
// public class tests {
//     @Autowired
//     private GameController RestController;

//     @MockBean
//     Private UserRepository repository;

//     public void getUsersTest(){
//         when(repository.findAll()).thenReturn(Stream
//                 .of(new User(101,"John","Admin"),
//                         new User(102,"Smith","User"))).collect(Collectors.toList());
//         assertEquals(2,service.getUsers().size());
//     }   
// }

package sports_betting;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.beans.Transient;

import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional

public class BBAAPIConrollerTest{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllTeams() throws Exception {
        mockMvc.perform(get("/api/teams"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", isA(java.util.List.class)))
                .andExpect(jsonPath("$", hasSize(greaterThan(0))));
    }

    @Testvoid testGetTeamById() throws Exception {
        mockMvc.perform(get("/api/teams/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").exists());
    }

    @Test
    void testGetTeamById_NotFound() throws Exception {
        mockMvc.perform(get("/api/teams/9999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateTeam() throws Exception {
        NBATeam newTeam = new NBATeam();
        newTeam.setName("Test Team");
        newTeam.setCity("Test City");
        newTeam.Conference("Eastern");
        newTeam.setDivision("Atlantic");

        mockMvc.perform(post("/api/teams")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newTeam)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.name").value("Test Team"))
            .andExpect(jsonPath("$.city").value("Test City"))
            .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void testCreateTeam_InvalidData() throws Exception {
        NBATeam newTeam = new NBATeam();

        mockMvc.perform(post("/api/teams")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidTeam)))
            .andExpect(status().isBadRequest());
    }

    @Test 
    void testUpdateTeam() throws Exception {
        NBATeam updatedTeam = new NBATeam();
        updatedTeam.setId(1);
        updatedTeam.setName("Updated Team Name");
        updatedTeam.setCity("Updated City");
        updatedTeam.setConference("Western");
        updatedTeam.setDivision("Pacific");

        mockMvc.perform(put("/api/teams/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedTeam)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Updated Team Name"))
            .andExpect(jsonPath("$.city").value("Updated City"));
    }

    @Test
    void testUpdateTeam_NotFound() throws Exception {
        NBATeam updatedTeam = new NBATeam();
        updatedTeam.setId(9999);
        updatedTeam.setName("Non-existent Team");

        mockMvc.perform(put("/api/teams/9999")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedTeam)))
            .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteTeam() throws Exception {
        NBATeam teamToDelete = new NBATeam();
        teamToDelete.setName("Team To Delete");
        teamToDelete.setCity("Delete City");
        teamToDelete.setConference("Eastern");
        teamToDelete.setDivision("Atlantic");

        String response = mockMvc.perform(post("/api/teams")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(teamToDelete)))
            .andExpect(status().isCreated())
            .andReturn()
            .getResponse()
            .getContentAsString();

        NBATeam createdTeam = objectMapper.readValue(response, NBATeam.class);

        mockMvc.perform(delete("/api/teams/" + createdTeam.getId()))
            .andExpect(status().isNoContent());
        
        mockMvc.perform(get("/api/teams/" + createdTeam.getId()))
            .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteTeam_NotFound() throws Exception {
        mockMvc.perform(delete("/api/teams/9999"))
            .andExpect(status().isNotFound());
    }

    @Test
    void testGetTeamsByConference() throws Exception {
        mockMvc.perform(get("/api/teams")
                .param("conference", "Eastern"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[*].conference", everyItem(is("Eastern"))));
    }

}


// import org.junit.jupiter.api.*;

// class CalculatorTest {
//     // private Calculator calc;

//     @BeforeAll
//     static void initAll() { System.out.println("Starting Calculator Tests"); }

//     @AfterEach
//     void tearDown() { /* cleanup if needed */ }

//     @AfterAll
//     static void tearDownAll() { System.out.println("All tests done"); }
// }