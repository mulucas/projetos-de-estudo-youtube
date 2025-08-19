package com.javanauta.posto_combustivel.service;

import com.javanauta.posto_combustivel.infrastructure.entities.TipoCombustivel;
import com.javanauta.posto_combustivel.infrastructure.repositories.TipoCombustivelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoCombustivelService {

    private final TipoCombustivelRepository tipoCombustivelRepository;

    public void criar (TipoCombustivel bombasDeCombustivel){
        tipoCombustivelRepository.save(bombasDeCombustivel);
    }

    public TipoCombustivel buscarTipoCombustivelPorId (Integer id){
        return tipoCombustivelRepository.findById(id).orElseThrow(() ->
                new NullPointerException("Tipo Combustivel não encontrado pelo id "+id));
    }

    public List<TipoCombustivel> buscarTiposDeCombustivel(){
        return tipoCombustivelRepository.findAll();
    }

    @Transactional
    public void deletarTipoCombustivelPorId(Integer id){
        tipoCombustivelRepository.deleteById(id);
    }

    public void alterarTipoCombustivel(Integer id, TipoCombustivel tipoCombustivel){
        TipoCombustivel tipoDeCombustivel = buscarTipoCombustivelPorId(id);
        tipoCombustivel.setId(tipoDeCombustivel.getId());
        tipoCombustivelRepository.save(tipoCombustivel);
    }

    public void deletarTiposCombustivel() {
        tipoCombustivelRepository.deleteAll();
    }
}
