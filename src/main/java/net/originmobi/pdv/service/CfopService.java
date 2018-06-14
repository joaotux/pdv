package net.originmobi.pdv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.originmobi.pdv.model.CFOP;
import net.originmobi.pdv.repository.CfopRepository;

@Service
public class CfopService {
	
	@Autowired
	private CfopRepository cfops;
	
	public List<CFOP> lista() {
		return cfops.findAll();
	}

}
