package com.javanauta.posto_combustivel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javanauta.posto_combustivel.infrastructure.entities.TipoCombustivel;
import com.javanauta.posto_combustivel.service.TipoCombustivelService;
import com.javanauta.posto_combustivel.exception.TipoCombustivelNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TipoCombustivelController.class)
class TipoCombustivelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TipoCombustivelService tipoDeCombustivelService;

    @Autowired
    private ObjectMapper objectMapper;

    private TipoCombustivel tipoCombustivel;

    @BeforeEach
    void setUp() {
        tipoCombustivel = new TipoCombustivel();
        tipoCombustivel.setId(1);
        tipoCombustivel.setNome("Gasolina Comum");
        tipoCombustivel.setPrecoPorLitro(new BigDecimal("5.50"));
    }

    @Test
    @DisplayName("Deve criar um novo tipo de combustivel e retornar status 202 Accepted")
    void testCriarTipoCombustivel_sucesso() throws Exception {

        doNothing().when(tipoDeCombustivelService).criar(any(TipoCombustivel.class));

        mockMvc.perform(post("/tiposDeCombustivel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tipoCombustivel)))
                .andExpect(status().isAccepted());
    }

    @Test
    @DisplayName("Deve retornar todos os tipos de combustivel com status 200 OK")
    void testBuscarTiposDeCombustivel_sucesso() throws Exception {

        given(tipoDeCombustivelService.buscarTiposDeCombustivel()).willReturn(List.of(tipoCombustivel));

        mockMvc.perform(get("/tiposDeCombustivel"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].nome").value("Gasolina Comum"));
    }

    @Test
    @DisplayName("Deve retornar tipo de combustivel por id com status 200 OK")
    void testBuscarTipoCombustivelPorId_sucesso() throws Exception {

        given(tipoDeCombustivelService.buscarTipoCombustivelPorId(1)).willReturn(tipoCombustivel);

        mockMvc.perform(get("/tiposDeCombustivel/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Gasolina Comum"));
    }

    @Test
    @DisplayName("Deve deletar um tipo de combustivel por id e retornar status 200 OK")
    void testDeletarTipoCombustivel_sucesso() throws Exception {

        doNothing().when(tipoDeCombustivelService).deletarTipoCombustivelPorId(1);

        mockMvc.perform(delete("/tiposDeCombustivel/1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve retornar status 404 Not Found quando tenta deletar tipo de combustivel inexistente")
    void testDeletarTipoCombustivel_naoEncontrado() throws Exception {

        doThrow(new TipoCombustivelNaoEncontradoException("Tipo de combustivel não encontrado"))
                .when(tipoDeCombustivelService).deletarTipoCombustivelPorId(999);

        mockMvc.perform(delete("/tiposDeCombustivel/999"))
                .andExpect(status().isNotFound());
    }

}
