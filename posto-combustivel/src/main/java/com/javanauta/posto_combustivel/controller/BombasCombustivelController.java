package com.javanauta.posto_combustivel.controller;

import com.javanauta.posto_combustivel.infrastructure.entities.BombaCombustivel;
import com.javanauta.posto_combustivel.service.BombaCombustivelService;
import com.javanauta.posto_combustivel.exception.BombaNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bombasDeCombustivel")
public class BombasCombustivelController {

    private final BombaCombustivelService bombaDeCombustivelService;

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody BombaCombustivel bombaDeCombustivel) {
        bombaDeCombustivelService.criar(bombaDeCombustivel);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<BombaCombustivel>> buscarBombaDeCombustivel() {
        return ResponseEntity.ok(bombaDeCombustivelService.buscarBombaDeCombustivel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BombaCombustivel> buscarBombaDeCombustivelPorId(@PathVariable Integer id) {
        try {
            BombaCombustivel bomba = bombaDeCombustivelService.buscarBombaCombustivelPorId(id);
            return ResponseEntity.ok(bomba);
        } catch (BombaNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarBombaDeCombustivelPorId(@PathVariable Integer id) {
        try {
            bombaDeCombustivelService.deletarBombaCombustivelPorId(id);
            return ResponseEntity.noContent().build();
        } catch (BombaNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BombaCombustivel> alterarBombaDeCombustivel(@PathVariable Integer id,
                                                                      @RequestBody BombaCombustivel bombaDeCombustivel) {
        bombaDeCombustivelService.alterarBombaCombustivel(id, bombaDeCombustivel);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(BombaNaoEncontradaException.class)
    public ResponseEntity<String> handleBombaNaoEncontradaException(BombaNaoEncontradaException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
