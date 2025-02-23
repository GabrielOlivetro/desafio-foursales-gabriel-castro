package com.gaba.games.controller;

import com.gaba.games.dto.PedidoDTO;
import com.gaba.games.model.Pedido;
import com.gaba.games.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody PedidoDTO pedidoDTO) {
        try {
            Pedido pedido = pedidoService.criarPedido(pedidoDTO.getUsuarioId(), pedidoDTO.getJogosIds());
            return ResponseEntity.ok(pedido);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/pagar/{pedidoId}")
    public ResponseEntity<Pedido> realizarPagamento(@PathVariable Long pedidoId) {
        try {
            Pedido pedido = pedidoService.realizarPagamento(pedidoId);
            return ResponseEntity.ok(pedido);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Pedido>> listarPedidos(@RequestParam Long usuarioId) {
        List<Pedido> pedidos = pedidoService.listarPedidosPorUsuario(usuarioId);
        return ResponseEntity.ok(pedidos);
    }
}
