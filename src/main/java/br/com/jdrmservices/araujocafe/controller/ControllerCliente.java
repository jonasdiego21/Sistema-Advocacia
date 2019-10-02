package br.com.jdrmservices.araujocafe.controller;

import static br.com.jdrmservices.araujocafe.util.Constantes.VIEW_CADASTRO_CLIENTE;
import static br.com.jdrmservices.araujocafe.util.Constantes.VIEW_PESQUISA_CLIENTE;
import static br.com.jdrmservices.araujocafe.util.Constantes.REDIRECT_CADASTRO_CLIENTE;
import static br.com.jdrmservices.araujocafe.util.Constantes.INFORMACOES_CADASTRADAS_SUCESSO;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

import br.com.jdrmservices.araujocafe.controller.page.PageWrapper;
import br.com.jdrmservices.araujocafe.model.Cliente;
import br.com.jdrmservices.araujocafe.model.constants.EstadoCivil;
import br.com.jdrmservices.araujocafe.model.constants.Sexo;
import br.com.jdrmservices.araujocafe.repository.Clientes;
import br.com.jdrmservices.araujocafe.repository.Estados;
import br.com.jdrmservices.araujocafe.repository.filter.ClienteFilter;
import br.com.jdrmservices.araujocafe.services.ServiceCliente;
import br.com.jdrmservices.araujocafe.services.exceptions.NomeClienteJaCadastradoException;

@Controller
@RequestMapping("/clientes")
public class ControllerCliente {
	
	@Autowired
	private ServiceCliente serviceCliente;
	
	@Autowired
	private Estados estados;
	
	@Autowired
	private Clientes clientes;
	
	@GetMapping("/novo")
	public ModelAndView novo(Cliente cliente) {
		ModelAndView mv = new ModelAndView(VIEW_CADASTRO_CLIENTE);
		mv.addObject("acao", "ADICIONAR");
		mv.addObject("sexos", Sexo.values());
		mv.addObject("estados", estados.findAll());
		mv.addObject("estadosCivis", EstadoCivil.values());
		
		return mv;
	}	
	
	@PostMapping
	public ModelAndView salvar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			return novo(cliente);
		}

		try {
			serviceCliente.salvar(cliente);			
		} catch (NomeClienteJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(cliente);
		}
	
		attributes.addFlashAttribute("mensagem", INFORMACOES_CADASTRADAS_SUCESSO);
		
		return new ModelAndView(REDIRECT_CADASTRO_CLIENTE);	
	}

	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Cliente cliente) {	
		ModelAndView mv = novo(cliente);
		mv.addObject(cliente);
		
		return mv;
	}
	
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Cliente cliente) {
		try {
			serviceCliente.excluir(cliente);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.ok().build();	
	}
	
	@GetMapping
	public ModelAndView pesquisar(ClienteFilter clienteFilter, @PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {		
		ModelAndView mv = new ModelAndView(VIEW_PESQUISA_CLIENTE);
		
		Page<Cliente> page = clientes.filtrar(pageable);
		PageWrapper<Cliente> pageWrapper = new PageWrapper<>(page, httpServletRequest);
		
		mv.addObject("pagina", pageWrapper);
		mv.addObject("acao", "PESQUISAR");
		
		mv.addObject("clientes", clientes.filtrar(clienteFilter));
		
		return mv;
	}
	
}
