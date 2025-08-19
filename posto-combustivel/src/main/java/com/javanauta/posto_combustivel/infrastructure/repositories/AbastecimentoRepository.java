package com.javanauta.posto_combustivel.infrastructure.repositories;

import com.javanauta.posto_combustivel.infrastructure.entities.Abastecimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbastecimentoRepository extends JpaRepository<Abastecimento, Integer> {
}
