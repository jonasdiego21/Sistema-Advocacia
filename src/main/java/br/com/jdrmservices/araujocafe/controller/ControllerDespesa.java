package br.com.jdrmservices.araujocafe.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jdrmservices.araujocafe.model.Despesa;
import br.com.jdrmservices.araujocafe.services.ServiceDespesa;

@RestController
@RequestMapping("/despesas")
public class ControllerDespesa {

	@Autowired
	private ServiceDespesa serviceDespesa;
	
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> salvar( @RequestBody @Valid Despesa despesa, BindingResult result) {
		
		/*
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getAllErrors());
		}
		
		Despesa despesaSalva = serviceDespesa.salvar(despesa);
		
		return ResponseEntity.ok(despesaSalva);
		*/
		System.out.println(despesa.getDescricao());
		return ResponseEntity.ok("Ok!");
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<?> excluir( @PathVariable("codigo") Despesa despesa) {
		serviceDespesa.excluir(despesa);
		return ResponseEntity.ok().build();
	}
	
}
