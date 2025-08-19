package com.javanauta.posto_combustivel.controller;

import com.javanauta.posto_combustivel.infrastructure.entities.Abastecimento;
import com.javanauta.posto_combustivel.service.AbastecimentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(AbastecimentoController.class)
class AbastecimentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AbastecimentoService abastecimentoService;

    private Abastecimento abastecimento;

    @BeforeEach
    void setUp() {
        abastecimento = new Abastecimento();
        abastecimento.setId(1);
        abastecimento.setQuantidadeLitros(50L);
        abastecimento.setValoTotal(new BigDecimal("275.00"));
        abastecimento.setDataAbastecimento(LocalDate.now());
    }

    @Test
    @DisplayName("Deve realizar um abastecimento e retornar status 202 Accepted")
    void testAbastecer_sucesso() throws Exception {

        doNothing().when(abastecimentoService).abastecer(anyInt(), anyLong());

        mockMvc.perform(post("/abastecimento")
                        .param("quantidadeEmLitros", "50")
                        .param("idBomba", "1"))
                .andExpect(status().isAccepted());
    }

    @Test
    @DisplayName("Deve retornar todos os abastecimentos com status 200 OK")
    void testBuscarAbastecimentos_sucesso() throws Exception {

        given(abastecimentoService.buscarAbastecimentos()).willReturn(List.of(abastecimento));

        mockMvc.perform(get("/abastecimento"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].quantidadeLitros").value(50L))
                .andExpect(jsonPath("$.[0].valoTotal").value(275.00));
    }
}
