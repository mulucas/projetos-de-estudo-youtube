package com.javanauta.posto_combustivel.service;

import com.javanauta.posto_combustivel.infrastructure.entities.Abastecimento;
import com.javanauta.posto_combustivel.infrastructure.entities.BombaCombustivel;
import com.javanauta.posto_combustivel.infrastructure.repositories.AbastecimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AbastecimentoService {

    private final AbastecimentoRepository abastecimentoRepository;
    private final BombaCombustivelService bombaDeCombustivelService;

    public void abastecer(Integer idBomba, Long litros){
        BombaCombustivel bomba = bombaDeCombustivelService.buscarBombaCombustivelPorId(idBomba);

        BigDecimal valorTotal = bomba.getTipoCombustivel()
                .getPrecoPorLitro().multiply(BigDecimal.valueOf(litros));

        Abastecimento abastecimento = Abastecimento.builder()
                .dataAbastecimento(LocalDate.now())
                .bombasDeCombustivel(bomba)
                .valoTotal(valorTotal)
                .quantidadeLitros(litros)
                .build();

        abastecimentoRepository.save(abastecimento);
    }

    public List<Abastecimento> buscarAbastecimentos(){
        return abastecimentoRepository.findAll();
    }
}
