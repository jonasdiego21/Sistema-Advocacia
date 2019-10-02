package br.com.jdrmservices.araujocafe.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.jdrmservices.araujocafe.model.DocumentoApresentado;
import br.com.jdrmservices.araujocafe.services.ServiceDocumentoApresentado;

@Controller
public class ControllerDocumentoApresentado {
	
	@Autowired
	private ServiceDocumentoApresentado serviceDocumentoApresentado;	
	
	public @ResponseBody ResponseEntity<?> salvar(@Valid DocumentoApresentado documentoApresentado) {
		try {
			serviceDocumentoApresentado.salvar(documentoApresentado);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.ok().build();	
	}
	
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") DocumentoApresentado documentoApresentado) {
		try {
			serviceDocumentoApresentado.excluir(documentoApresentado);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.ok().build();	
	}
}