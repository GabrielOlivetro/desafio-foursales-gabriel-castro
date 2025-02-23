package com.gaba.games.controller;

import com.gaba.games.service.RelatorioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/relatorios")
@RequiredArgsConstructor
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping("/top5-clientes")
    public ResponseEntity<List<Map<String, Object>>> getTop5UsuariosMaisCompraram() {
        return ResponseEntity.ok(relatorioService.getTop5UsuariosMaisCompraram());
    }

    @GetMapping("/ticket-medio")
    public ResponseEntity<List<Map<String, Object>>> calcularTicketMedioPorUsuario() {
        return ResponseEntity.ok(relatorioService.calcularTicketMedioPorUsuario());
    }

    @GetMapping("/faturamento-mensal")
    public ResponseEntity<BigDecimal> calcularFaturamentoMensal() {
        return ResponseEntity.ok(relatorioService.calcularFaturamentoMensal());
    }
}
