package com.javanauta.posto_combustivel.infrastructure.repositories;

import com.javanauta.posto_combustivel.infrastructure.entities.BombaCombustivel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BombaCombustivelRepository extends JpaRepository<BombaCombustivel, Integer> {
}
