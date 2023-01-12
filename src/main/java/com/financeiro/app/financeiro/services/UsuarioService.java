package com.financeiro.app.financeiro.services;

import com.financeiro.app.financeiro.exception.RegraNegocioExeption;
import com.financeiro.app.financeiro.model.dto.UsuarioAutenticacaoDTO;
import com.financeiro.app.financeiro.model.entity.Usuario;

import java.util.Optional;

public interface UsuarioService {

    Usuario autenticar(UsuarioAutenticacaoDTO dto);

    Usuario salvarUsuario(Usuario usuario);

    void validarEmail(String email);

    Optional<Usuario> obterPorId(Long id);

}
