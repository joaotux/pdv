package net.originmobi.pdv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.originmobi.pdv.model.CstCsosn;
import net.originmobi.pdv.repository.CstCsosnRepository;

@Service
public class CstCsosnService {

	@Autowired
	private CstCsosnRepository repository;

	public List<CstCsosn> lista() {
		return repository.findAll();
	}

}
