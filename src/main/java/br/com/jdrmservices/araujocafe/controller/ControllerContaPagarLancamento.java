package br.com.jdrmservices.araujocafe.controller;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.jdrmservices.araujocafe.model.ContaPagarLancamento;
import br.com.jdrmservices.araujocafe.repository.ContasPagar;
import br.com.jdrmservices.araujocafe.services.ServiceContaPagarLancamento;
import br.com.jdrmservices.araujocafe.services.exceptions.ContaPagarLancamentoException;
import br.com.jdrmservices.araujocafe.util.Constantes;

@Controller
@RequestMapping("/contasPagarLancamento")
public class ControllerContaPagarLancamento {
	
	@Autowired
	private ServiceContaPagarLancamento serviceContaPagarLancamento;
	
	@Autowired
	private ContasPagar contasPagar;
	
	@GetMapping("/nova")
	public ModelAndView nova(ContaPagarLancamento contaPagarLancamento) {
		ModelAndView mv = new ModelAndView(Constantes.VIEW_CONTA_PAGAR_LANCAMENTO);
		mv.addObject("acao", Constantes.ACAO_NENHUMA);
		mv.addObject("contasPagar", contasPagar.findAll());
		
		return mv;
	}
	
	@PostMapping
	public ModelAndView salvar(@Valid ContaPagarLancamento contaPagarLancamento, BindingResult result, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			return nova(contaPagarLancamento);
		}
		
		try {
			serviceContaPagarLancamento.salvar(contaPagarLancamento);
		} catch (ContaPagarLancamentoException e) {
			result.rejectValue("codigo", e.getMessage(), e.getMessage());
			return nova(contaPagarLancamento);
		}
		
		attributes.addFlashAttribute("mensagem", Constantes.INFORMACOES_CADASTRADAS_SUCESSO);
		
		return new ModelAndView(Constantes.REDIRECT_CONTA_PAGAR_LANCAMENTO);
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") ContaPagarLancamento contaPagarLancamento) {
		ModelAndView mv = new ModelAndView(Constantes.VIEW_CONTA_PAGAR_LANCAMENTO);
		mv.addObject("acao", Constantes.ACAO_NENHUMA);
		mv.addObject(contaPagarLancamento);
		return mv;
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<?> excluir(@PathVariable("codigo") ContaPagarLancamento contaPagarLancamento) {
		try {
			serviceContaPagarLancamento.excluir(contaPagarLancamento);
		} catch (Exception e) {
			ResponseEntity.badRequest().body(Constantes.ERRO_EXCLUIR_INFORMACOES);
		}
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public ModelAndView pesquisar() {
		ModelAndView mv = new ModelAndView(Constantes.VIEW_CONTA_PAGAR_LANCAMENTO);
		mv.addObject("acao", Constantes.ACAO_NENHUMA);
		mv.addObject("contasPagarLancamento", serviceContaPagarLancamento.listar());
		return mv;
	}
}
