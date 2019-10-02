package br.com.jdrmservices.araujocafe.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import br.com.jdrmservices.araujocafe.dto.DocumentoDTO;
import br.com.jdrmservices.araujocafe.storage.DocumentoStorageRunnable;

@RestController
@RequestMapping("/documentos")
public class ControllerDocumento {

	@PostMapping
	public DeferredResult<DocumentoDTO> upload(@RequestParam("files[]") MultipartFile[] files) {
		DeferredResult<DocumentoDTO> resultado = new DeferredResult<>();
		
		Thread thread = new Thread(new DocumentoStorageRunnable(files, resultado));
		thread.start();
		
		return resultado;
	}
	
}
