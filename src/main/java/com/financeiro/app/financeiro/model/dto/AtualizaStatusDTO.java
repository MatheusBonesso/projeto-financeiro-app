package com.financeiro.app.financeiro.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AtualizaStatusDTO {

    @JsonProperty("status")
    private String statusLancamento;
}
