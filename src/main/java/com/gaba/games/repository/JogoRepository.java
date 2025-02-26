package com.gaba.games.repository;

import com.gaba.games.model.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JogoRepository extends JpaRepository<Jogo, Long> {
    Optional<Jogo> findByNome(String nome);
}