package br.com.jdrmservices.araujocafe.controller;

import static br.com.jdrmservices.araujocafe.util.Constantes.VIEW_CADASTRO_AGENDA;
import static br.com.jdrmservices.araujocafe.util.Constantes.VIEW_PESQUISA_AGENDA;
import static br.com.jdrmservices.araujocafe.util.Constantes.REDIRECT_CADASTRO_AGENDA;
import static br.com.jdrmservices.araujocafe.util.Constantes.INFORMACOES_CADASTRADAS_SUCESSO;

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

import br.com.jdrmservices.araujocafe.model.Agenda;
import br.com.jdrmservices.araujocafe.repository.Agendas;
import br.com.jdrmservices.araujocafe.services.ServiceAgenda;
import br.com.jdrmservices.araujocafe.services.exceptions.TituloAgendaJaCadastradoException;

@Controller
@RequestMapping("/agendas")
public class ControllerAgenda {
	
	@Autowired
	private ServiceAgenda serviceAgenda;
	
	@Autowired
	private Agendas agendas;
	
	@GetMapping("/nova")
	public ModelAndView nova(Agenda agenda) {
		ModelAndView mv = new ModelAndView(VIEW_CADASTRO_AGENDA);
		mv.addObject("acao", "ADICIONAR");
		return mv;
	}
	
	@PostMapping
	public ModelAndView salvar(@Valid Agenda agenda, BindingResult result, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			return nova(agenda);
		}

		try {
			serviceAgenda.salvar(agenda);			
		} catch (TituloAgendaJaCadastradoException e) {
			result.rejectValue("titulo", e.getMessage(), e.getMessage());
			return nova(agenda);
		}
	
		attributes.addFlashAttribute("mensagem", INFORMACOES_CADASTRADAS_SUCESSO);
		
		return new ModelAndView(REDIRECT_CADASTRO_AGENDA);	
	}

	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Agenda agenda) {	
		ModelAndView mv = nova(agenda);
		mv.addObject(agenda);
		
		return mv;
	}
	
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Agenda agenda) {
		try {
			serviceAgenda.excluir(agenda);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.ok().build();	
	}
	
	@GetMapping
	public ModelAndView pesquisar() {
		ModelAndView mv = new ModelAndView(VIEW_PESQUISA_AGENDA);
		mv.addObject("acao", "PESQUISAR");
		mv.addObject("agendas", agendas.findAll());
		return mv;
	}
	
}
