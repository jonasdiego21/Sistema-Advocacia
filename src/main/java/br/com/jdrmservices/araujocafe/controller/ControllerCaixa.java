package br.com.jdrmservices.araujocafe.controller;

import static br.com.jdrmservices.araujocafe.util.Constantes.VIEW_CAIXA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.jdrmservices.araujocafe.services.ServiceDashboard;

@Controller
@RequestMapping("/caixas")
public class ControllerCaixa {

	@Autowired
	private ServiceDashboard serviceDashboard;
	
	@GetMapping
	public ModelAndView valores() {
		ModelAndView mv = new ModelAndView(VIEW_CAIXA);
		mv.addObject("acao", "");
		
		// despesas
		mv.addObject("valorTotalDespesasNoMes", serviceDashboard.valorTotalDespesasNoMes());
		mv.addObject("totalPagoNoMes", serviceDashboard.totalPagoNoMes());
		
		// receitas
		mv.addObject("valorTotalReceitasNoMes", serviceDashboard.valorTotalReceitasNoMes());
		mv.addObject("totalRecebidoNoMes", serviceDashboard.valorTotalRecebidoNoMes());
		
		// restante
		mv.addObject("restante", serviceDashboard.valorTotalReceitasNoMes().subtract(serviceDashboard.valorTotalDespesasNoMes()));
		
		return mv;
	}
}
