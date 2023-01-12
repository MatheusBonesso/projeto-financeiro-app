package com.financeiro.app.financeiro.controller;

import com.financeiro.app.financeiro.exception.AutenticacaoException;
import com.financeiro.app.financeiro.exception.RegraNegocioExeption;
import com.financeiro.app.financeiro.model.dto.UsuarioAutenticacaoDTO;
import com.financeiro.app.financeiro.model.dto.UsuarioDTO;
import com.financeiro.app.financeiro.model.entity.Usuario;
import com.financeiro.app.financeiro.model.mapper.UsuarioMapper;
import com.financeiro.app.financeiro.services.LancamentoService;
import com.financeiro.app.financeiro.services.UsuarioService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {


    private final UsuarioService usuarioService;

    private final UsuarioMapper mapper;

    private final LancamentoService lancamentoService;


    @PostMapping("/")
    public ResponseEntity saveUsuario(@RequestBody UsuarioDTO dto) {
        Usuario usuario = mapper.usuarioDTOToUsuario(dto);

        try {
            Usuario result = usuarioService.salvarUsuario(usuario);
            return new ResponseEntity(result, HttpStatus.CREATED);
        } catch (RegraNegocioExeption e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/autenticar")
    public ResponseEntity autenticar(@RequestBody UsuarioAutenticacaoDTO dto) {

        try {
            Usuario result = usuarioService.autenticar(dto);
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (AutenticacaoException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}/saldo")
    public ResponseEntity obterSaldo(@PathVariable("id") Long id) {
        Optional<Usuario> usuario = usuarioService.obterPorId(id);
        if(!usuario.isPresent())
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        BigDecimal saldo = lancamentoService.obterSaldoUsuario(id);

        return new ResponseEntity(saldo, HttpStatus.OK);


    }
}
