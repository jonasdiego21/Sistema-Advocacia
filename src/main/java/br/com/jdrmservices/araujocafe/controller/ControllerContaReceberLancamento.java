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

import br.com.jdrmservices.araujocafe.model.ContaReceberLancamento;
import br.com.jdrmservices.araujocafe.repository.ContasReceber;
import br.com.jdrmservices.araujocafe.services.ServiceContaReceberLancamento;
import br.com.jdrmservices.araujocafe.util.Constantes;

@Controller
@RequestMapping("/contasReceberLancamento")
public class ControllerContaReceberLancamento {

	@Autowired
	private ServiceContaReceberLancamento serviceContaReceberLancamento;
	
	@Autowired
	private ContasReceber contasReceber;
	
	@GetMapping("/nova")
	public ModelAndView nova(ContaReceberLancamento contaReceberLancamento) {
		ModelAndView mv = new ModelAndView(Constantes.VIEW_CONTA_RECEBER_LANCAMENTO);
		mv.addObject("acao", Constantes.ACAO_NENHUMA);
		mv.addObject("contasReceber", contasReceber.findAll());		
		return mv;
	}
	
	@PostMapping
	public ModelAndView salvar(@Valid ContaReceberLancamento contaReceberLancamento, BindingResult result, RedirectAttributes attribute) {
		if(result.hasErrors()) {
			return nova(contaReceberLancamento);
		}
		
		try {
			serviceContaReceberLancamento.salvar(contaReceberLancamento);
		} catch (Exception e) {
			result.rejectValue("codigo", e.getMessage(), e.getMessage());
			return nova(contaReceberLancamento);
		}
		
		attribute.addFlashAttribute("mensagem", Constantes.INFORMACOES_CADASTRADAS_SUCESSO);
		
		return new ModelAndView(Constantes.REDIRECT_CONTA_RECEBER_LANCAMENTO);
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") ContaReceberLancamento contaReceberLancamento) {
		ModelAndView mv = new ModelAndView(Constantes.VIEW_CONTA_RECEBER_LANCAMENTO);
		mv.addObject("acao", Constantes.ACAO_NENHUMA);
		mv.addObject(contaReceberLancamento);
		return mv;
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<?> excluir(@PathVariable("codigo") ContaReceberLancamento contaReceberLancamento) {
		try {
			serviceContaReceberLancamento.excluir(contaReceberLancamento);
		} catch (Exception e) {
			ResponseEntity.badRequest().body(Constantes.ERRO_EXCLUIR_INFORMACOES);
		}
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public ModelAndView pesquisar() {
		ModelAndView mv = new ModelAndView(Constantes.VIEW_CONTA_RECEBER_LANCAMENTO);
		mv.addObject("contasReceberLancamento", serviceContaReceberLancamento.listar());
		mv.addObject("acao", Constantes.ACAO_NENHUMA);
		return mv;
	}
	
}
