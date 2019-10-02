package br.com.jdrmservices.araujocafe.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jdrmservices.araujocafe.model.DocumentoApresentado;
import br.com.jdrmservices.araujocafe.repository.DocumentosApresentados;

@Service
public class ServiceDocumentoApresentado {

	@Autowired
	private DocumentosApresentados documentosApresentados;
	
	@Transactional
	public void salvar(DocumentoApresentado documentoApresentado) {
		Optional<DocumentoApresentado> optionalDocumentoApresentado = documentosApresentados.findByNome(documentoApresentado.getNome());
		
		if(optionalDocumentoApresentado.isPresent()) {
			documentosApresentados.saveAndFlush(documentoApresentado);
		}
	}

	@Transactional
	public void excluir(DocumentoApresentado documentoApresentado) {
		documentosApresentados.delete(documentoApresentado);
	}

}
