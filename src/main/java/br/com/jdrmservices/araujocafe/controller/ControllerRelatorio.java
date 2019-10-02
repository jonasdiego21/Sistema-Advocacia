package br.com.jdrmservices.araujocafe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.jdrmservices.araujocafe.util.Constantes;

@Controller
@RequestMapping("/relatorios")
public class ControllerRelatorio {

	@GetMapping
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(Constantes.VIEW_RELATORIOS);
		
		return mv;
	}
	
}
