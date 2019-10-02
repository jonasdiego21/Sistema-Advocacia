package br.com.jdrmservices.araujocafe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.jdrmservices.araujocafe.model.constants.StatusProcesso;
import br.com.jdrmservices.araujocafe.repository.Processos;
import br.com.jdrmservices.araujocafe.services.ServiceDashboard;

import static br.com.jdrmservices.araujocafe.util.Constantes.VIEW_DASHBOARD;

@Controller
@RequestMapping("/")
public class ControllerDashboard {

	@Autowired
	private Processos processos;
	
	@Autowired
	private ServiceDashboard serviceDashboard;

	@GetMapping
	public ModelAndView dashboard() {
		ModelAndView mv = new ModelAndView(VIEW_DASHBOARD);
		// processo
		mv.addObject("protocolado", processos.findByStatus(StatusProcesso.PROTOCOLADO).size());
		mv.addObject("aguardandoAudienciaPericia", processos.findByStatus(StatusProcesso.AGUARDANDO_AUDIENCIA_PERICIA).size());
		mv.addObject("julgadoPrimeiraInstancia", processos.findByStatus(StatusProcesso.JULGADO_PRIMEIRA_INSTANCIA).size());
		mv.addObject("julgadoSegundaInstancia", processos.findByStatus(StatusProcesso.JULGADO_SEGUNDA_INSTANCIA).size());
		mv.addObject("transitadoEmJulgado", processos.findByStatus(StatusProcesso.TRANSITADO_EM_JULGADO).size());
		mv.addObject("emExecucao", processos.findByStatus(StatusProcesso.EM_EXECUCAO).size());
		mv.addObject("encerrado", processos.findByStatus(StatusProcesso.ENCERRADO).size());
		mv.addObject("arquivado", processos.findByStatus(StatusProcesso.ARQUIVADO).size());
		mv.addObject("totalProcessosCadastrados", processos.count());
		
		// Agenda
		mv.addObject("totalCompromissosHoje", serviceDashboard.totalCompromissosHoje());
		
		// qtde despesas
		mv.addObject("totalContasPagarHoje", serviceDashboard.totalContasPagarHoje());
		mv.addObject("totalContasReceberHoje", serviceDashboard.totalContasReceberHoje());
		
		// despesas
		mv.addObject("valorTotalDespesasNoMes", serviceDashboard.valorTotalDespesasNoMes());
		mv.addObject("valorTotalDespesasHoje", serviceDashboard.valorTotalDespesasHoje());
		mv.addObject("totalPagoNoMes", serviceDashboard.totalPagoNoMes());
		
		// despesas
		mv.addObject("valorTotalReceitasNoMes", serviceDashboard.valorTotalReceitasNoMes());
		mv.addObject("valorTotalReceitasHoje", serviceDashboard.valorTotalReceitasHoje());
		mv.addObject("totalRecebidoNoMes", serviceDashboard.valorTotalRecebidoNoMes());

		return mv;
	}	
}
