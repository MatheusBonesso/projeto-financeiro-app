package com.financeiro.app.financeiro.model.dto;

import com.financeiro.app.financeiro.model.entity.Usuario;
import com.financeiro.app.financeiro.model.enums.StatusLancamento;
import com.financeiro.app.financeiro.model.enums.TipoLancamento;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class LancamentoDTO {

    private Long id;

    private Integer mes;

    private Integer ano;

    private String descricao;

    private Usuario usuario;

    private BigDecimal valor;

    private TipoLancamento tipoLancamento;

    private StatusLancamento statusLancamento;
}
