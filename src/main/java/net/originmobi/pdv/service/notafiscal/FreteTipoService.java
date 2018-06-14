package net.originmobi.pdv.service.notafiscal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.originmobi.pdv.model.FreteTipo;
import net.originmobi.pdv.repository.notafiscal.FreteTipoRepository;

@Service
public class FreteTipoService {

	@Autowired
	private FreteTipoRepository repository;

	public List<FreteTipo> lista() {
		return repository.findAll();
	}

}
