package com.financeiro.app.financeiro.model.mapper;

import com.financeiro.app.financeiro.model.dto.UsuarioDTO;
import com.financeiro.app.financeiro.model.entity.Usuario;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDTO usuarioToUsuarioDTO(Usuario usuario);

    Usuario usuarioDTOToUsuario(UsuarioDTO usuarioDTO);
}
