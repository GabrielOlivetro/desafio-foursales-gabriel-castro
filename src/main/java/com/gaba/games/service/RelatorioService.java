package com.gaba.games.service;

import com.gaba.games.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RelatorioService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Map<String, Object>> getTop5UsuariosMaisCompraram() {
        return pedidoRepository.findTop5UsuariosMaisCompraram().stream()
                .map(obj -> Map.of(
                        "id", obj[0],
                        "nome", obj[1],
                        "totalPedidos", obj[2]
                ))
                .collect(Collectors.toList());
    }

    public List<Map<String, Object>> calcularTicketMedioPorUsuario() {
        return pedidoRepository.calcularTicketMedioPorUsuario().stream()
                .map(obj -> Map.of(
                        "id", obj[0],
                        "nome", obj[1],
                        "ticketMedio", obj[2]
                ))
                .collect(Collectors.toList());
    }

    public BigDecimal calcularFaturamentoMensal() {
        return pedidoRepository.calcularFaturamentoMensal();
    }
}
