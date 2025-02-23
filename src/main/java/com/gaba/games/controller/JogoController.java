package com.gaba.games.controller;

import com.gaba.games.model.Jogo;
import com.gaba.games.service.JogoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jogos")
@RequiredArgsConstructor
public class JogoController {

    @Autowired
    private JogoService jogoService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Jogo> insert(@RequestBody Jogo jogo) {
        return ResponseEntity.ok(jogoService.insert(jogo));
    }

    @GetMapping
    public ResponseEntity<List<Jogo>> listarJogos() {
        return ResponseEntity.ok(jogoService.listarJogos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jogo> buscarPorId(@PathVariable Long id) {
        Optional<Jogo> jogo = jogoService.findById(id);
        return jogo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Jogo> update(@PathVariable Long id, @RequestBody Jogo jogo) {
        return ResponseEntity.ok(jogoService.update(id, jogo));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        jogoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
