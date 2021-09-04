package net.originmobi.pdv.repository;

import java.sql.Timestamp;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.originmobi.pdv.enumerado.VendaSituacao;
import net.originmobi.pdv.model.PagamentoTipo;
import net.originmobi.pdv.model.Pessoa;
import net.originmobi.pdv.model.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {

	public Page<Venda> findByCodigoIn(Long codigo, Pageable pageable);

	@Transactional
	@Modifying
	@Query("update Venda v set v.pessoa = :pessoa, v.observacao = :observacao where v.codigo = :codigo")
	public void updateDadosVenda(@Param("pessoa") Pessoa pessoa, @Param("observacao") String observacao, @Param("codigo") Long codigo);

	@Query("select v.situacao from Venda v where v.codigo = ?1")
	public String verificaSituacao(Long codigo);

	@Query("select v.valorProdutos from Venda v where v.codigo = ?1")
	public Double verificaValorProdutos(Long codVen);

	@Transactional
	@Modifying
	@Query("update Venda v set v.valorProdutos = :valorProdutos where v.codigo = :codigo")
	public void atualizaValorProdutos(@Param("valorProdutos") Double vlProdutos, @Param("codigo") Long codigo);

	@Query("select v from Venda v where v.situacao = ?1")
	public Page<Venda> findBySituacaoEquals(VendaSituacao situacao, Pageable pageable);

	public Venda findByCodigoEquals(Long codigo);

	@Transactional
	@Modifying
	@Query("update Venda set situacao = :situacao, valorTotal = :vlTotal, valorDesconto = :vlDesconto, valorAcrescimo = :vlAcrescimo, dataFinalizado = :dataFinalizado, "
			+ "pagamentotipo = :formaPagamento where codigo = :codigo and dataFinalizado is null")
	public void fechaVenda(@Param("codigo") Long codVenda, @Param("situacao") VendaSituacao situacao,
			@Param("vlTotal") Double vltotal, @Param("vlDesconto") Double vldesconto,
			@Param("vlAcrescimo") Double vlacrescimo, @Param("dataFinalizado") Timestamp dataFinalizado,
			@Param("formaPagamento") PagamentoTipo formaPagamento);

	@Query("select count(*) from Venda where dataFinalizado is null")
	public int qtdVendasEmAberto();
}
