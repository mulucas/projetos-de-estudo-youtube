package com.javanauta.posto_combustivel.service;

import com.javanauta.posto_combustivel.exception.BombaNaoEncontradaException;
import com.javanauta.posto_combustivel.infrastructure.entities.BombaCombustivel;
import com.javanauta.posto_combustivel.infrastructure.repositories.BombaCombustivelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BombaCombustivelService {

    private final BombaCombustivelRepository bombaCombustivelRepository;

    public void criar (BombaCombustivel bombasDeCombustivel){
        bombaCombustivelRepository.save(bombasDeCombustivel);
    }

    public BombaCombustivel buscarBombaCombustivelPorId(Integer id) {
        return bombaCombustivelRepository.findById(id)
                .orElseThrow(() -> new BombaNaoEncontradaException("Bomba não encontrada pelo id " + id));
    }

    public List<BombaCombustivel> buscarBombaDeCombustivel(){
        return bombaCombustivelRepository.findAll();
    }

    @Transactional
    public void deletarBombaCombustivelPorId(Integer id){
        BombaCombustivel bomba = buscarBombaCombustivelPorId(id);
        bombaCombustivelRepository.delete(bomba);
    }

    public void alterarBombaCombustivel(Integer id, BombaCombustivel bombaDeCombustivel){
        BombaCombustivel bomba = buscarBombaCombustivelPorId(id);
        bombaDeCombustivel.setId(bomba.getId());
        bombaCombustivelRepository.save(bombaDeCombustivel);
    }

    public void deletarBombas() {
        bombaCombustivelRepository.deleteAll();
    }
}
