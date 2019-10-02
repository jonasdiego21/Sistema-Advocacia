package br.com.jdrmservices.araujocafe.controller;

import static br.com.jdrmservices.araujocafe.util.Constantes.INFORMACOES_CADASTRADAS_SUCESSO;
import static br.com.jdrmservices.araujocafe.util.Constantes.REDIRECT_CADASTRO_CONTA_RECBER;
import static br.com.jdrmservices.araujocafe.util.Constantes.VIEW_CADASTRO_CONTA_RECEBER;
import static br.com.jdrmservices.araujocafe.util.Constantes.VIEW_PESQUISA_CONTA_RECEBER;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.jdrmservices.araujocafe.dto.TotalReceitasNoAno;
import br.com.jdrmservices.araujocafe.dto.TotalReceitasNoMes;
import br.com.jdrmservices.araujocafe.model.ContaReceber;
import br.com.jdrmservices.araujocafe.model.constants.Status;
import br.com.jdrmservices.araujocafe.repository.Clientes;
import br.com.jdrmservices.araujocafe.repository.ContasReceber;
import br.com.jdrmservices.araujocafe.repository.Processos;
import br.com.jdrmservices.araujocafe.services.ServiceContaReceber;
import br.com.jdrmservices.araujocafe.services.exceptions.NumeroProcessoContaReceberJaCadastradoException;

@Controller
@RequestMapping("/contasReceber")
public class ControllerContaReceber {
	
	@Autowired
	private Clientes clientes;
	
	@Autowired
	private ContasReceber contasReceber;
	
	@Autowired
	private Processos processos;
	
	@Autowired
	private ServiceContaReceber serviceContaReceber;
	
	@GetMapping("/nova")
	public ModelAndView nova(ContaReceber contaReceber) {
		ModelAndView mv = new ModelAndView(VIEW_CADASTRO_CONTA_RECEBER);
		mv.addObject("acao", "ADICIONAR");
		mv.addObject("clientes", clientes.findAll());
		mv.addObject("processos", processos.findAll());
		mv.addObject("status", Status.values());

		return mv;
	}
	
	@PostMapping
	public ModelAndView salvar(@Valid ContaReceber contaReceber, BindingResult result, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			return nova(contaReceber);
		}

		try {
			serviceContaReceber.salvar(contaReceber);			
		} catch (NumeroProcessoContaReceberJaCadastradoException e) {
			result.rejectValue("numeroProcesso", e.getMessage(), e.getMessage());
			return nova(contaReceber);
		}
	
		attributes.addFlashAttribute("mensagem", INFORMACOES_CADASTRADAS_SUCESSO);
		
		return new ModelAndView(REDIRECT_CADASTRO_CONTA_RECBER);	
	}

	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") ContaReceber contaReceber) {	
		ModelAndView mv = nova(contaReceber);
		mv.addObject(contaReceber);
		
		return mv;
	}
	
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") ContaReceber contaReceber) {
		try {
			serviceContaReceber.excluir(contaReceber);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.ok().build();	
	}
	
	@GetMapping
	public ModelAndView pesquisar() {
		ModelAndView mv = new ModelAndView(VIEW_PESQUISA_CONTA_RECEBER);
		mv.addObject("acao", "PESQUISAR");
		mv.addObject("contasReceber", contasReceber.findAll());
		return mv;
	}
	
	// Gráficos
	@GetMapping("/totalReceitasNoMes")
	public @ResponseBody List<TotalReceitasNoMes> totalDespesasNoMes() {
		return contasReceber.totalReceitasNoMes();
	}
	
	// Gráficos
	@GetMapping("/totalReceitasNoAno")
	public @ResponseBody List<TotalReceitasNoAno> totalReceitasNoAno() {
		return contasReceber.totalReceitasNoAno();
	}
}
