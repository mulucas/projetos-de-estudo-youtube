package com.javanauta.posto_combustivel.infrastructure.repositories;

import com.javanauta.posto_combustivel.infrastructure.entities.TipoCombustivel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoCombustivelRepository extends JpaRepository<TipoCombustivel, Integer> {
}
