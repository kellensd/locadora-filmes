package com.project.locadora.controller;

import com.project.locadora.configuration.CustomAuthenticationProvider;
import com.project.locadora.dto.FilmeDTO;
import com.project.locadora.service.impl.FilmeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FilmeController.class)
public class FilmeControllerTest {

    @MockBean
    private FilmeServiceImpl filmeService;

    @MockBean
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    private MockMvc mockMvc;

    FilmeDTO filmeDTO;

    @Before
    public void setUp() {
        filmeDTO = new FilmeDTO();
        filmeDTO.setDiretor("Eu");
        filmeDTO.setQuantidade(10);
        filmeDTO.setTitulo("cascata");
    }


    @Test
    @WithMockUser(username = "kellen", password = "teste123", roles = "USER")
    public void findByTituloTest() throws Exception {
        when(filmeService.findByTitulo("cascata")).thenReturn(Optional.of(filmeDTO));

        mockMvc.perform(get("/filmes/{titulo}", "cascata"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("diretor").value("Eu"));
    }

    @Test
    @WithMockUser(username = "kellen", password = "teste123", roles = "USER")
    public void findByTituloNotFountTest() throws Exception {
        when(filmeService.findByTitulo("cascata")).thenReturn(Optional.empty());

        mockMvc.perform(get("/filmes/{titulo}", "cascata"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "kellen", password = "teste123", roles = "USER")
    public void findFilmesDisponiveisTest() throws Exception {
        when(filmeService.findFilmesDisponiveis()).thenReturn(List.of(filmeDTO));

        mockMvc.perform(get("/filmes/disponiveis"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].diretor").value("Eu"));
    }

    @Test
    @WithMockUser(username = "kellen", password = "teste123", roles = "USER")
    public void findFilmesDisponiveisNotFountTest() throws Exception {
        when(filmeService.findFilmesDisponiveis()).thenReturn(List.of());

        mockMvc.perform(get("/filmes/disponiveis"))
                .andExpect(status().isNotFound());
    }
}