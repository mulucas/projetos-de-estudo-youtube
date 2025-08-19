package com.javanauta.posto_combustivel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javanauta.posto_combustivel.infrastructure.entities.BombaCombustivel;
import com.javanauta.posto_combustivel.infrastructure.entities.TipoCombustivel;
import com.javanauta.posto_combustivel.service.BombaCombustivelService;
import com.javanauta.posto_combustivel.service.TipoCombustivelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BombasCombustivelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BombaCombustivelService bombaDeCombustivelService;

    @Autowired
    private TipoCombustivelService tipoCombustivelService;

    @Autowired
    private ObjectMapper objectMapper;

    private TipoCombustivel tipoCombustivel;

    @BeforeEach
    void setup() {
        bombaDeCombustivelService.deletarBombas();
        tipoCombustivelService.deletarTiposCombustivel();

        tipoCombustivel = TipoCombustivel.builder()
                .nome("Gasolina Aditivada")
                .precoPorLitro(new BigDecimal("6.50"))
                .build();
        tipoCombustivelService.criar(tipoCombustivel);
    }

    @Test
    void testCriarBomba_sucesso() throws Exception {
        BombaCombustivel bomba = BombaCombustivel.builder()
                .nome("Bomba de Teste")
                .tipoCombustivel(tipoCombustivel)
                .build();

        mockMvc.perform(post("/bombasDeCombustivel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bomba)))
                .andExpect(status().isAccepted());
    }

    @Test
    void testBuscarTodasBombas_sucesso() throws Exception {
        BombaCombustivel bomba1 = BombaCombustivel.builder()
                .nome("Bomba de Teste 1")
                .tipoCombustivel(tipoCombustivel)
                .build();
        bombaDeCombustivelService.criar(bomba1);

        mockMvc.perform(get("/bombasDeCombustivel")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nome", is("Bomba de Teste 1")));
    }

    @Test
    void testBuscarBombaPorId_sucesso() throws Exception {
        BombaCombustivel bomba = BombaCombustivel.builder()
                .nome("Bomba para Busca por ID")
                .tipoCombustivel(tipoCombustivel)
                .build();
        bombaDeCombustivelService.criar(bomba);
        Integer idBomba = bomba.getId();

        mockMvc.perform(get("/bombasDeCombustivel/{id}", idBomba)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is("Bomba para Busca por ID")));
    }

    @Test
    void testBuscarBombaPorId_naoEncontrado() throws Exception {
        mockMvc.perform(get("/bombasDeCombustivel/{id}", 999)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testAlterarBombaDeCombustivel_sucesso() throws Exception {
        BombaCombustivel bombaOriginal = BombaCombustivel.builder()
                .nome("Bomba Antiga")
                .tipoCombustivel(tipoCombustivel)
                .build();
        bombaDeCombustivelService.criar(bombaOriginal);
        Integer idBomba = bombaOriginal.getId();

        BombaCombustivel bombaAtualizada = BombaCombustivel.builder()
                .nome("Bomba Nova")
                .tipoCombustivel(tipoCombustivel)
                .build();

        mockMvc.perform(put("/bombasDeCombustivel/{id}", idBomba)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bombaAtualizada)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/bombasDeCombustivel/{id}", idBomba))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is("Bomba Nova")));
    }

    @Test
    void testDeletarBombaDeCombustivel_sucesso() throws Exception {
        BombaCombustivel bomba = BombaCombustivel.builder()
                .nome("Bomba para Deletar")
                .tipoCombustivel(tipoCombustivel)
                .build();
        bombaDeCombustivelService.criar(bomba);
        Integer idBomba = bomba.getId();

        mockMvc.perform(delete("/bombasDeCombustivel/{id}", idBomba))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/bombasDeCombustivel/{id}", idBomba))
                .andExpect(status().isNotFound());
    }
}
