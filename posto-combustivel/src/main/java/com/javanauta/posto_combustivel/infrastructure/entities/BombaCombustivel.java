package com.javanauta.posto_combustivel.infrastructure.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bombas_de_combustivel")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BombaCombustivel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "combustivel_id")
    private TipoCombustivel tipoCombustivel;
}
