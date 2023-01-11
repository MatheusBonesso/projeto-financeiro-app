package com.financeiro.app.financeiro.services;

import com.financeiro.app.financeiro.model.entity.Usuario;

public interface UsuarioService {

    Usuario autenticar(String email, String senha);

    Usuario salvarUsuario(Usuario usuario);

    void validarEmail(String email);

}
