package com.gaba.games.service;

import com.gaba.games.model.Jogo;
import com.gaba.games.model.Pedido;
import com.gaba.games.model.StatusPedido;
import com.gaba.games.model.Usuario;
import com.gaba.games.repository.JogoRepository;
import com.gaba.games.repository.PedidoRepository;
import com.gaba.games.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private JogoRepository jogoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Pedido criarPedido(Long usuarioId, List<Long> jogosIds) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        List<Jogo> jogos = jogoRepository.findAllById(jogosIds);

        for (Jogo jogo : jogos) {
            if (jogo.getQuantidadeEmEstoque() <= 0) {
                throw new RuntimeException("Jogo " + jogo.getNome() + " não tem estoque suficiente.");
            }
        }

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setJogos(jogos);
        pedido.calcularValorTotal();

        pedidoRepository.save(pedido);

        return pedido;
    }

    @Transactional
    public Pedido realizarPagamento(Long pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        if (pedido.getStatus() == StatusPedido.PAGO) {
            throw new RuntimeException("O pagamento deste pedido já foi realizado.");
        }

        for (Jogo jogo : pedido.getJogos()) {
            if (jogo.getQuantidadeEmEstoque() <= 0) {
                pedido.atualizarStatus(StatusPedido.CANCELADO);
                pedidoRepository.save(pedido);
                throw new RuntimeException("Pedido cancelado, estoque insuficiente para o jogo " + jogo.getNome());
            }
            jogo.setQuantidadeEmEstoque(jogo.getQuantidadeEmEstoque() - 1);
            jogoRepository.save(jogo);
        }

        pedido.atualizarStatus(StatusPedido.PAGO);
        pedidoRepository.save(pedido);

        return pedido;
    }

    public List<Pedido> listarPedidosPorUsuario(Long usuarioId) {
        return pedidoRepository.findByUsuarioId(usuarioId);
    }
}
