package com.financeiro.app.financeiro.services;

import com.financeiro.app.financeiro.model.entity.Lancamento;
import com.financeiro.app.financeiro.model.enums.StatusLancamento;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface LancamentoService {

    Lancamento salvar(Lancamento lancamento);

    Lancamento atualizar(Lancamento lancamento);

    void deletar(Lancamento lancamento);

    List<Lancamento> buscar(Lancamento lancamentoFiltro);

    void atualizarStatus(Lancamento lancamento, StatusLancamento status);

    Optional<Lancamento> obterPorId(Long id);

    BigDecimal obterSaldoUsuario(Long id);

}
