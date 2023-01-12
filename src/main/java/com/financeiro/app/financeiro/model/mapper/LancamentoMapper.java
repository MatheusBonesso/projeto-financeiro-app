package com.financeiro.app.financeiro.model.mapper;

import com.financeiro.app.financeiro.model.dto.LancamentoDTO;
import com.financeiro.app.financeiro.model.entity.Lancamento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LancamentoMapper {

    Lancamento lancamentoDTOToLancamento(LancamentoDTO dto);

    LancamentoDTO lancamentoToLancamentoDTO(Lancamento lancamento);
}
