package net.originmobi.pdv.service.notafiscal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.originmobi.pdv.model.ModBcIcms;
import net.originmobi.pdv.repository.notafiscal.ModBcIcmsRepository;

@Service
public class ModBcIcmsService {

	@Autowired
	private ModBcIcmsRepository repository;

	public List<ModBcIcms> lista() {
		return repository.findAll();
	}

}
