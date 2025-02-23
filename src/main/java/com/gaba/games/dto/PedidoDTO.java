package com.gaba.games.dto;

import lombok.Data;

import java.util.List;

@Data
public class PedidoDTO {
    private Long usuarioId;
    private List<Long> jogosIds;

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public List<Long> getJogosIds() {
        return jogosIds;
    }

    public void setJogosIds(List<Long> jogosIds) {
        this.jogosIds = jogosIds;
    }
}
