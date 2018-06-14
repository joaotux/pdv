package net.originmobi.pdv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.originmobi.pdv.model.Caixa;
import net.originmobi.pdv.model.CaixaLancamento;

public interface CaixaLancamentoRepository extends JpaRepository<CaixaLancamento, Long> {

	public List<CaixaLancamento> findByCaixaEquals(Caixa caixa);

}
