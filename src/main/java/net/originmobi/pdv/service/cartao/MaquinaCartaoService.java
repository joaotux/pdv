package net.originmobi.pdv.service.cartao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.originmobi.pdv.model.cartao.MaquinaCartao;
import net.originmobi.pdv.repository.cartao.MaquinaCartaoRepository;

@Service
public class MaquinaCartaoService {

	@Autowired
	private MaquinaCartaoRepository maquinas;

	public void cadastrar(MaquinaCartao maquinaCartao) {
		try {
			maquinas.save(maquinaCartao);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public List<MaquinaCartao> lista() {
		return maquinas.findAll();
	}

	public void remove(Long codigo) {
		try {
			maquinas.deleteById(codigo);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao tentar remover a máquina, chame o suporte");
		}
	}

}
