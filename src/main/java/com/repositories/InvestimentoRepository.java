package com.repositories;

import com.domains.FaturaCartao;
import com.domains.Investimento;
import com.domains.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvestimentoRepository extends JpaRepository<Investimento, Long> {

    List<Investimento> findByUsuario_IdUsuario(Long idUsuario);
}
