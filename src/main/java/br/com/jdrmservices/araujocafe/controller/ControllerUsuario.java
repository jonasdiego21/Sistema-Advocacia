package br.com.jdrmservices.araujocafe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.jdrmservices.araujocafe.exception.NegocioException;
import br.com.jdrmservices.araujocafe.model.Usuario;
import br.com.jdrmservices.araujocafe.repository.Grupos;
import br.com.jdrmservices.araujocafe.services.ServiceUsuario;
import br.com.jdrmservices.araujocafe.util.Constantes;

import javax.validation.Valid;

@Controller
@RequestMapping("/usuarios")
public class ControllerUsuario {

	@Autowired
	private ServiceUsuario serviceUsuario;
	
	@Autowired
	private Grupos grupos;
	
	@GetMapping("/novo")
	public ModelAndView novo(Usuario usuario) {
		ModelAndView mv = new ModelAndView(Constantes.VIEW_CADASTRO_USUARIO);
		mv.addObject("grupos", grupos.findAll());
		mv.addObject("acao", "ADICIONAR");
		return mv;
	}
	
	@PostMapping
	public ModelAndView salvar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			return novo(usuario);
		}
		
		try {
			serviceUsuario.salvar(usuario);
			attributes.addFlashAttribute("mensagem", Constantes.INFORMACOES_CADASTRADAS_SUCESSO);
		}catch (NegocioException e) {
			result.reject(e.getMessage(), e.getMessage());
			return novo(usuario);
		}
		
		return new ModelAndView(Constantes.REDIRECT_CADASTRO_USUARIO);
	}
	
}
