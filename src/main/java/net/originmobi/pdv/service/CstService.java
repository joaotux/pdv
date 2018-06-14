package net.originmobi.pdv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.originmobi.pdv.model.Cst;
import net.originmobi.pdv.repository.CstRepository;

@Service
public class CstService {

	@Autowired
	private CstRepository csts;

	public List<Cst> lista() {
		return csts.findAll();
	}

}
