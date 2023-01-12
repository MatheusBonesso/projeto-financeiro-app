package com.financeiro.app.financeiro.controller;

import com.financeiro.app.financeiro.exception.RegraNegocioExeption;
import com.financeiro.app.financeiro.model.dto.AtualizaStatusDTO;
import com.financeiro.app.financeiro.model.dto.LancamentoDTO;
import com.financeiro.app.financeiro.model.entity.Lancamento;
import com.financeiro.app.financeiro.model.entity.Usuario;
import com.financeiro.app.financeiro.model.enums.StatusLancamento;
import com.financeiro.app.financeiro.model.mapper.LancamentoMapper;
import com.financeiro.app.financeiro.services.LancamentoService;
import com.financeiro.app.financeiro.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/lancamentos")
@RequiredArgsConstructor
public class LancamentoController {

    private final LancamentoService lancamentoService;

    private final UsuarioService usuarioService;

    private final LancamentoMapper mapper;

    @PostMapping
    public ResponseEntity salvar(@RequestBody LancamentoDTO dto){

        if(!usuarioService.obterPorId(dto.getUsuario().getId()).isPresent())
            throw new RegraNegocioExeption("Usuario não encontrado");

        try{
            Lancamento result = lancamentoService.salvar(mapper.lancamentoDTOToLancamento(dto));
            return new ResponseEntity(result, HttpStatus.CREATED);

        }catch (RegraNegocioExeption e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody LancamentoDTO dto){

        if(!lancamentoService.obterPorId(id).isPresent())
            throw new RegraNegocioExeption("Lancamento não encontrado");
        try{

            Lancamento result = lancamentoService.atualizar(mapper.lancamentoDTOToLancamento(dto));
            return new ResponseEntity(result, HttpStatus.OK);

        }catch (RegraNegocioExeption e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletar(@PathVariable("id") Long id){
        Optional<Lancamento> lancamento = lancamentoService.obterPorId(id);
        if(!lancamento.isPresent())
            throw new RegraNegocioExeption("Lancamento não encontrado");

        try{
            lancamentoService.deletar(lancamento.get());
            return new ResponseEntity("Lancamento deletado", HttpStatus.OK);
        }catch (RegraNegocioExeption e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity buscar(@RequestParam(value = "descricao", required = false) String descricao,
                                 @RequestParam(value = "mes", required = false) Integer mes,
                                 @RequestParam(value = "ano", required = false) Integer ano,
                                 @RequestParam("idUsuario") Long idUsuario ){
        Lancamento lancamentoFiltro = new Lancamento();
        lancamentoFiltro.setDescricao(descricao);
        lancamentoFiltro.setMes(mes);
        lancamentoFiltro.setAno(ano);

        Optional<Usuario> usuario = usuarioService.obterPorId(idUsuario);
        if(!usuario.isPresent())
            return new ResponseEntity("Usuario nao encontrado", HttpStatus.BAD_REQUEST);
        lancamentoFiltro.setUsuario(usuario.get());


        return new ResponseEntity<>(lancamentoService.buscar(lancamentoFiltro), HttpStatus.OK);


    }

    @PutMapping("{id}/atualiza-status")
    public ResponseEntity atualizarStatus(@PathVariable("id") Long id ,@RequestBody AtualizaStatusDTO dto){

        Optional<Lancamento> lancamento = lancamentoService.obterPorId(id);

        if(!lancamento.isPresent())
            throw new RegraNegocioExeption("Lancamento não encontrado");
        if(dto.getStatusLancamento() == null)
            throw new RegraNegocioExeption("Lancamento invalido");

        lancamento.get().setStatusLancamento(StatusLancamento.valueOf(dto.getStatusLancamento()));
        Lancamento result = lancamentoService.atualizar(lancamento.get());

        return new ResponseEntity(result, HttpStatus.OK);

    }




}
