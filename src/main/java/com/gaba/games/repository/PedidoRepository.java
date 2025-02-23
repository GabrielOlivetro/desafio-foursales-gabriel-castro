package com.gaba.games.repository;

import com.gaba.games.model.Pedido;
import com.gaba.games.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByUsuarioId(Long idUsuario);

    @Query(value = """
                SELECT u.id, u.nome, COUNT(p.id) AS total_pedidos 
                FROM pedido p 
                JOIN usuario u ON p.usuario_id = u.id 
                WHERE p.status = 'PAGO'
                GROUP BY u.id, u.nome 
                ORDER BY total_pedidos DESC 
                LIMIT 5
            """, nativeQuery = true)
    List<Object[]> findTop5UsuariosMaisCompraram();

    @Query(value = """
                SELECT u.id, u.nome, AVG(p.valor_total) AS ticket_medio
                FROM pedido p 
                JOIN usuario u ON p.usuario_id = u.id 
                WHERE p.status = 'PAGO'
                GROUP BY u.id, u.nome
            """, nativeQuery = true)
    List<Object[]> calcularTicketMedioPorUsuario();

    @Query(value = """
                SELECT COALESCE(SUM(p.valor_total), 0) AS faturamento_total
                FROM pedido p 
                WHERE p.status = 'PAGO' 
                AND MONTH(p.data_criacao) = MONTH(CURRENT_DATE) 
                AND YEAR(p.data_criacao) = YEAR(CURRENT_DATE)
            """, nativeQuery = true)
    BigDecimal calcularFaturamentoMensal();
}