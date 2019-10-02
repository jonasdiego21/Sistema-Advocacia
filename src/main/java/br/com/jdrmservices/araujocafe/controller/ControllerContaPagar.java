package br.com.jdrmservices.araujocafe.controller;

import static br.com.jdrmservices.araujocafe.util.Constantes.INFORMACOES_CADASTRADAS_SUCESSO;
import static br.com.jdrmservices.araujocafe.util.Constantes.REDIRECT_CADASTRO_CONTA_PAGAR;
import static br.com.jdrmservices.araujocafe.util.Constantes.VIEW_CADASTRO_CONTA_PAGAR;
import static br.com.jdrmservices.araujocafe.util.Constantes.VIEW_PESQUISA_CONTA_PAGAR;

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

import br.com.jdrmservices.araujocafe.dto.TotalDespesasNoAno;
import br.com.jdrmservices.araujocafe.dto.TotalDespesasNoMes;
import br.com.jdrmservices.araujocafe.model.ContaPagar;
import br.com.jdrmservices.araujocafe.model.constants.Status;
import br.com.jdrmservices.araujocafe.repository.ContasPagar;
import br.com.jdrmservices.araujocafe.services.ServiceContaPagar;
import br.com.jdrmservices.araujocafe.services.exceptions.FornecedorContaPagarJaCadastradoException;
import br.com.jdrmservices.araujocafe.util.Constantes;

@Controller
@RequestMapping("/contasPagar")
public class ControllerContaPagar {
	
	@Autowired
	private ContasPagar contasPagar;
	
	@Autowired
	private ServiceContaPagar serviceContaPagar;
	
	@GetMapping("/nova")
	public ModelAndView nova(ContaPagar contaPagar) {
		ModelAndView mv = new ModelAndView(VIEW_CADASTRO_CONTA_PAGAR);
		mv.addObject("acao", Constantes.ACAO_ADICIONAR);
		mv.addObject("status", Status.values());
		return mv;
	}
	
	@PostMapping
	public ModelAndView salvar(@Valid ContaPagar contaPagar, BindingResult result, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			return nova(contaPagar);
		}

		try {
			serviceContaPagar.salvar(contaPagar);			
		} catch (FornecedorContaPagarJaCadastradoException e) {
			result.rejectValue("fornecedor", e.getMessage(), e.getMessage());
			return nova(contaPagar);
		}
	
		attributes.addFlashAttribute("mensagem", INFORMACOES_CADASTRADAS_SUCESSO);
		
		return new ModelAndView(REDIRECT_CADASTRO_CONTA_PAGAR);	
	}

	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") ContaPagar contaPagar) {	
		ModelAndView mv = nova(contaPagar);
		mv.addObject(contaPagar);
		
		return mv;
	}
	
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") ContaPagar contaPagar) {
		try {
			serviceContaPagar.excluir(contaPagar);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.ok().build();	
	}
	
	@GetMapping
	public ModelAndView pesquisar() {
		ModelAndView mv = new ModelAndView(VIEW_PESQUISA_CONTA_PAGAR);
		mv.addObject("acao", "PESQUISAR");
		mv.addObject("contasPagar", contasPagar.findAll());
		return mv;
	}
	
	// Gráficos
	@GetMapping("/totalDespesasNoMes")
	public @ResponseBody List<TotalDespesasNoMes> totalDespesasNoMes() {
		return contasPagar.totalDespesasNoMes();
	}
	
	// Gráficos
	@GetMapping("/totalDespesasNoAno")
	public @ResponseBody List<TotalDespesasNoAno> totalDespesasNoAno() {
		return contasPagar.totalDespesasNoAno();
	}
	
}