package com.financeiro.app.financeiro.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.financeiro.app.financeiro.model.entity.Usuario;
import com.financeiro.app.financeiro.model.enums.StatusLancamento;
import com.financeiro.app.financeiro.model.enums.TipoLancamento;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class LancamentoDTO {

    private Long id;

    @JsonProperty("mes")
    private Integer mes;

    @JsonProperty("ano")
    private Integer ano;

    @JsonProperty("descricao")
    private String descricao;

    @JsonProperty("usuario")
    private Usuario usuario;

    @JsonProperty("valor")
    private BigDecimal valor;

    @JsonProperty("tipo")
    private TipoLancamento tipoLancamento;

    @JsonProperty("status")
    private StatusLancamento statusLancamento;

}
