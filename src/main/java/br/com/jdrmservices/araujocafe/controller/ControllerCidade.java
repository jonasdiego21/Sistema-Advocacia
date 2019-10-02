package br.com.jdrmservices.araujocafe.controller;
	
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.jdrmservices.araujocafe.model.Cidade;
import br.com.jdrmservices.araujocafe.repository.Cidades;
	
@RestController
@RequestMapping("/cidades")
public class ControllerCidade {
	
	@Autowired
	private Cidades cidades;
	
	@GetMapping
	public List<Cidade> cidadesPorEstado( @RequestParam(name = "codigo", defaultValue = "-1") Long codigoEstado) {
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {}
		
		return cidades.findByEstadoCodigo(codigoEstado);
	}
	
}