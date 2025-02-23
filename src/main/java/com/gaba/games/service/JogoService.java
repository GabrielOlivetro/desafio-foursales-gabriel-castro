package com.gaba.games.service;

import com.gaba.games.model.Jogo;
import com.gaba.games.repository.JogoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JogoService {

    @Autowired
    private JogoRepository jogoRepository;

    public Jogo insert(Jogo produto) {
        return jogoRepository.save(produto);
    }

    public List<Jogo> listarJogos() {
        return jogoRepository.findAll();
    }

    public Optional<Jogo> findById(Long id) {
        return jogoRepository.findById(id);
    }

    public Jogo update(Long id, Jogo jogo) {
        return jogoRepository.findById(id)
                .map(game -> {
                    game.setNome(jogo.getNome());
                    game.setDescricao(jogo.getDescricao());
                    game.setPreco(jogo.getPreco());
                    game.setCategoria(jogo.getCategoria());
                    game.setQuantidadeEmEstoque(jogo.getQuantidadeEmEstoque());
                    return jogoRepository.save(game);
                }).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public void delete(Long id) {
        jogoRepository.deleteById(id);
    }
}

