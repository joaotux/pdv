package net.originmobi.pdv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import net.originmobi.pdv.service.PagarParcelaService;
import net.originmobi.pdv.service.ReceberService;
import net.originmobi.pdv.service.VendaService;
import net.originmobi.pdv.service.notafiscal.NotaFiscalService;

@Controller
public class HomeController {

	private static final String DASHBOARD_DASHBOARD = "dashboard/dashboard";

	@Autowired
	private VendaService vendas;

	@Autowired
	private ReceberService receberServ;

	@Autowired
	private PagarParcelaService pagarServ;

	@Autowired
	private NotaFiscalService notas;

	@GetMapping({ "", "/" })
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView(DASHBOARD_DASHBOARD);
		mv.addObject("pedidosAberto", vendas.qtdAbertos());
		mv.addObject("total_a_receber", receberServ.totalAReceber());
		mv.addObject("total_a_pagar", pagarServ.totalParagarAberto());
		mv.addObject("total_notas_emitidas", notas.totalNotaFiscalEmitidas());

		return mv;
	}

	@GetMapping("/login")
	public String login() {
		return "login/login";
	}
	
}
