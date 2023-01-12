package com.financeiro.app.financeiro.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioAutenticacaoDTO {

    private String email;

    private String senha;
}
