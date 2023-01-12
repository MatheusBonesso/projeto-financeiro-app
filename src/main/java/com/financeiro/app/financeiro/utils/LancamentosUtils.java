package com.financeiro.app.financeiro.utils;

import com.financeiro.app.financeiro.exception.RegraNegocioExeption;
import com.financeiro.app.financeiro.model.entity.Lancamento;

import java.math.BigDecimal;

public class LancamentosUtils {


    public static void validar(Lancamento lancamento) {

        if (lancamento.getDescricao() == null || lancamento.getDescricao().trim().equals(""))
            throw new RegraNegocioExeption("Informe uma descrição válida");
        if (lancamento.getMes() == null || lancamento.getMes() < 1 || lancamento.getMes() > 12)
            throw new RegraNegocioExeption("Informe um mes valido");
        if (lancamento.getAno() == null || lancamento.getAno().toString().length() != 4)
            throw new RegraNegocioExeption("Informe um ano valido");
        if (lancamento.getUsuario() == null || lancamento.getUsuario().getId() == null)
            throw new RegraNegocioExeption("Informe um usuario valido");
        if (lancamento.getValor() == null || lancamento.getValor().compareTo(BigDecimal.ZERO) < 1)
            throw new RegraNegocioExeption("Informe um valor valido");
        if (lancamento.getTipoLancamento() == null)
            throw new RegraNegocioExeption("Informe um valor valido");


    }
}
