package com.javanauta.posto_combustivel.controller;

import com.javanauta.posto_combustivel.exception.BombaNaoEncontradaException;
import com.javanauta.posto_combustivel.infrastructure.entities.Abastecimento;
import com.javanauta.posto_combustivel.service.AbastecimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/abastecimento")
public class AbastecimentoController {

    private final AbastecimentoService abastecimentoService;

    @PostMapping
    public ResponseEntity<Void> abastecer(@RequestParam("quantidadeEmLitros") Long litros,
                                          @RequestParam("idBomba") Integer idBomba){
        abastecimentoService.abastecer(idBomba, litros);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<Abastecimento>> buscarAbastecimentos(){
        return ResponseEntity.ok(abastecimentoService.buscarAbastecimentos());
    }

    @ExceptionHandler(BombaNaoEncontradaException.class)
    public ResponseEntity<String> handleBombaNaoEncontradaException(BombaNaoEncontradaException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
