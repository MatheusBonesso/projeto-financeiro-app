package com.financeiro.app.financeiro.services.impl;

import com.financeiro.app.financeiro.exception.RegraNegocioExeption;
import com.financeiro.app.financeiro.model.entity.Lancamento;
import com.financeiro.app.financeiro.model.enums.StatusLancamento;
import com.financeiro.app.financeiro.model.enums.TipoLancamento;
import com.financeiro.app.financeiro.repositories.LancamentoRepository;
import com.financeiro.app.financeiro.services.LancamentoService;
import com.financeiro.app.financeiro.utils.LancamentosUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor

public class LancamentoServiceImpl implements LancamentoService {

    private LancamentoRepository repository;


    @Override
    @Transactional
    public Lancamento salvar(Lancamento lancamento) {
        LancamentosUtils.validar(lancamento);
        lancamento.setStatusLancamento(StatusLancamento.PENDENTE);
        return repository.save(lancamento);
    }

    @Override
    @Transactional
    public Lancamento atualizar(Lancamento lancamento) {
        Objects.requireNonNull(lancamento.getUsuario().getId());
        LancamentosUtils.validar(lancamento);
        return repository.save(lancamento);
    }

    @Override
    @Transactional
    public void deletar(Lancamento lancamento) {
        Objects.requireNonNull(lancamento.getId());
        repository.delete(lancamento);

    }

    @Override
    @Transactional(readOnly = true)
    public List<Lancamento> buscar( Lancamento lancamentoFiltro) {
        Example example = Example.of(lancamentoFiltro, ExampleMatcher
                .matching().withIgnoreCase()
                .withStringMatcher(ExampleMatcher
                .StringMatcher.CONTAINING));
        return repository.findAll(example);
    }

    @Override
    public void atualizarStatus(Lancamento lancamento, StatusLancamento status) {
        lancamento.setStatusLancamento(status);
        atualizar(lancamento);
    }

    @Override
    public Optional<Lancamento> obterPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public BigDecimal obterSaldoUsuario(Long id) {
        BigDecimal despesa = repository.obterSaldoPorLancamento(id, TipoLancamento.DESPESA);
        BigDecimal receita = repository.obterSaldoPorLancamento(id, TipoLancamento.RECEITA);
        if(despesa == null )
            despesa = BigDecimal.ZERO;
        if(receita == null)
            receita = BigDecimal.ZERO;

        return receita.subtract(despesa);
    }


}
