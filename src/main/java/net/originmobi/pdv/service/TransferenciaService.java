package net.originmobi.pdv.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.originmobi.pdv.model.Caixa;
import net.originmobi.pdv.model.Transferencia;
import net.originmobi.pdv.model.Usuario;
import net.originmobi.pdv.repository.TransferenciaRepository;
import net.originmobi.pdv.singleton.Aplicacao;
import net.originmobi.pdv.utilitarios.DataAtual;

@Service
public class TransferenciaService {

	@Autowired
	private TransferenciaRepository transferencias;

	@Autowired
	private UsuarioService usuarios;

	@Autowired
	private CaixaService caixas;

	public String cadastrar(Double valor, Long origem, Long destino, String obs) {
		Aplicacao aplicacao = Aplicacao.getInstancia();
		DataAtual dataAtual = new DataAtual();

		Usuario usuario = usuarios.buscaUsuario(aplicacao.getUsuarioAtual());

		Optional<Caixa> caiOrigem = caixas.busca(origem);
		Optional<Caixa> caiDestino = caixas.busca(destino);

		if (caiOrigem.equals(caiDestino))
			throw new RuntimeException("Destino é inválido");

		if (!caiOrigem.isPresent() || caiOrigem.map(Caixa::getDataFechamento).isPresent())
			throw new RuntimeException("Conta origem não esta aberta, verifique");

		if (!caiDestino.isPresent() || caiDestino.map(Caixa::getDataFechamento).isPresent())
			throw new RuntimeException("Conta destino não esta aberta, verifique");

		if (caiOrigem.map(Caixa::getValorTotal).get() < valor)
			throw new RuntimeException("Saldo insuficiente para realizar a transferência");

		Transferencia transferencia = new Transferencia(valor, dataAtual.dataAtualTimeStamp(), caiOrigem.get(),
				caiDestino.get(), usuario, "Transferencia para o " + caiDestino.map(Caixa::getDescricao).get() + " "
						+ caiDestino.map(Caixa::getCodigo).get());

		try {
			transferencias.save(transferencia);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao realizar a transferencia, chame o suporte");
		}

		return "Transferência realizada com sucesso";
	}

}
