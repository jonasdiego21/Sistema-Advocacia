package br.com.jdrmservices.araujocafe.controller;

import static br.com.jdrmservices.araujocafe.util.Constantes.VIEW_PESQUISA_PROCESSO;

import java.util.List;

import static br.com.jdrmservices.araujocafe.util.Constantes.VIEW_CADASTRO_PROCESSO;

import static br.com.jdrmservices.araujocafe.util.Constantes.INFORMACOES_CADASTRADAS_SUCESSO;
import static br.com.jdrmservices.araujocafe.util.Constantes.REDIRECT_CADASTRO_PROCESSO;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.jdrmservices.araujocafe.controller.page.PageWrapper;
import br.com.jdrmservices.araujocafe.dto.TotalProcessosPorStatus;
import br.com.jdrmservices.araujocafe.model.Processo;
import br.com.jdrmservices.araujocafe.model.constants.Foro;
import br.com.jdrmservices.araujocafe.model.constants.NaturezaAcao;
import br.com.jdrmservices.araujocafe.model.constants.StatusProcesso;
import br.com.jdrmservices.araujocafe.model.constants.Vara;
import br.com.jdrmservices.araujocafe.repository.Clientes;
import br.com.jdrmservices.araujocafe.repository.DocumentosApresentados;
import br.com.jdrmservices.araujocafe.repository.Estados;
import br.com.jdrmservices.araujocafe.repository.Processos;
import br.com.jdrmservices.araujocafe.repository.filter.ProcessoFilter;
import br.com.jdrmservices.araujocafe.services.ServiceProcesso;
import br.com.jdrmservices.araujocafe.services.exceptions.NumeroProcessoJaCadastradoException;

@Controller
@RequestMapping("/processos")
public class ControllerProcesso {

	@Autowired
	private Clientes clientes;
	
	@Autowired
	private Processos processos;
	
	@Autowired
	private ServiceProcesso serviceProcesso;
	
	@Autowired
	private DocumentosApresentados DocumentosApresentados;
	
	@Autowired
	private Estados estados;
	
	@GetMapping("/novo")
	public ModelAndView novo(Processo processo) {
		ModelAndView mv = new ModelAndView(VIEW_CADASTRO_PROCESSO);
		mv.addObject("acao", "ADICIONAR");
		mv.addObject("naturezaAcao", NaturezaAcao.values());
		mv.addObject("clientes", clientes.findAll());
		mv.addObject("documentos", DocumentosApresentados.findAll());
		mv.addObject("estados", estados.findAll());
		mv.addObject("status", StatusProcesso.values());
		mv.addObject("foros", Foro.values());
		mv.addObject("varas", Vara.values());
		return mv;
	}
	
	@PostMapping
	public ModelAndView salvar(@Valid Processo processo, BindingResult result, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			return novo(processo);
		}

		try {
			serviceProcesso.salvar(processo);			
		} catch (NumeroProcessoJaCadastradoException e) {
			result.rejectValue("numeroProcesso", e.getMessage(), e.getMessage());
			return novo(processo);
		}
	
		attributes.addFlashAttribute("mensagem", INFORMACOES_CADASTRADAS_SUCESSO);
		
		return new ModelAndView(REDIRECT_CADASTRO_PROCESSO);	
	}

	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Processo processo) {	
		ModelAndView mv = novo(processo);
		mv.addObject(processo);
		
		return mv;
	}	
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Processo processo) {
		try {
			serviceProcesso.excluir(processo);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.ok().build();	
	}
	
	@GetMapping
	public ModelAndView pesquisar(ProcessoFilter processoFilter, @PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView(VIEW_PESQUISA_PROCESSO);
		
		Page<Processo> page = processos.filtrar(pageable);
		PageWrapper<Processo> pageWrapper = new PageWrapper<>(page, httpServletRequest);
		
		mv.addObject("pagina", pageWrapper);
		mv.addObject("acao", "PESQUISAR");
		
		mv.addObject("processos", processos.filtrar(processoFilter));
		
		mv.addObject("clientes", clientes.findAll());
		mv.addObject("status", StatusProcesso.values());
		
		return mv;
	}
	
	// Api de gráficos
	
	@GetMapping("/totalProcessosPorStatus")
	public @ResponseBody List<TotalProcessosPorStatus> totalProcessoPorStatus(@RequestParam(name = "status", defaultValue = "") StatusProcesso status) {
		return processos.totalProcessosPorStatus(status);
	}
	
}
