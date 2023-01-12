package com.financeiro.app.financeiro.repositories;

import com.financeiro.app.financeiro.model.entity.Lancamento;
import com.financeiro.app.financeiro.model.enums.TipoLancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

    @Query("select sum(l.valor) from Lancamento l join l.usuario u where u.id= :idUsuario and l.tipoLancamento = :tipo group by u")
    BigDecimal obterSaldoPorLancamento(@Param("idUsuario") Long idUsuario,@Param("tipo") TipoLancamento tipoLancamento);

}
