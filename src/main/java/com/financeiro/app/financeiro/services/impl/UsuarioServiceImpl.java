package com.financeiro.app.financeiro.services.impl;

import com.financeiro.app.financeiro.exception.AutenticacaoException;
import com.financeiro.app.financeiro.exception.RegraNegocioExeption;
import com.financeiro.app.financeiro.model.dto.UsuarioAutenticacaoDTO;
import com.financeiro.app.financeiro.model.entity.Usuario;
import com.financeiro.app.financeiro.repositories.UsuarioRepository;
import com.financeiro.app.financeiro.services.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario autenticar(UsuarioAutenticacaoDTO dto)  {

        Optional<Usuario> user = usuarioRepository.findByEmail(dto.getEmail());

        if (!user.isPresent())
            throw new AutenticacaoException("Usuario não existe");
        if (!user.get().getSenha().equals(dto.getSenha()))
            throw new AutenticacaoException("Senha errada");

        return user.get();
    }

    @Override
    @Transactional
    public Usuario salvarUsuario(Usuario usuario) {
        validarEmail(usuario.getEmail());

        return usuarioRepository.save(usuario);
    }

    @Override
    public void validarEmail(String email){
        if (usuarioRepository.existsByEmail(email)) {
            throw new RegraNegocioExeption("Já existe um usuário com este email");
        }


    }

    @Override
    public Optional<Usuario> obterPorId(Long id) {
        return usuarioRepository.findById(id);
    }

}
