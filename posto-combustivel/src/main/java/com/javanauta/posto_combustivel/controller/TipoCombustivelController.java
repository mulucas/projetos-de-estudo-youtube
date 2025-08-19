package com.javanauta.posto_combustivel.controller;

import com.javanauta.posto_combustivel.exception.TipoCombustivelNaoEncontradoException;
import com.javanauta.posto_combustivel.infrastructure.entities.TipoCombustivel;
import com.javanauta.posto_combustivel.service.TipoCombustivelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tiposDeCombustivel")
public class TipoCombustivelController {

    private final TipoCombustivelService tipoDeCombustivelService;

    @PostMapping
    public ResponseEntity<Void> abastecer(@RequestBody TipoCombustivel tipoCombustivel) {
        tipoDeCombustivelService.criar(tipoCombustivel);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<TipoCombustivel>> buscarTipoCombustivel() {
        return ResponseEntity.ok(tipoDeCombustivelService.buscarTiposDeCombustivel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoCombustivel> buscarTipoCombustivelPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(tipoDeCombustivelService.buscarTipoCombustivelPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTipoCombustivelPorId(@PathVariable Integer id) {
        tipoDeCombustivelService.deletarTipoCombustivelPorId(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> alterarTipoDeCombustivel(@PathVariable Integer id,
                                                         @RequestBody TipoCombustivel tipoCombustivel) {
        tipoDeCombustivelService.alterarTipoCombustivel(id, tipoCombustivel);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(TipoCombustivelNaoEncontradoException.class)
    public ResponseEntity<String> handleTipoCombustivelNaoEncontradoException(TipoCombustivelNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
