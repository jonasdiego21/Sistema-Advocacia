package br.com.jdrmservices.araujocafe.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import br.com.jdrmservices.araujocafe.dto.ArquivoDTO;
import br.com.jdrmservices.araujocafe.storage.ArquivoStorageRunnable;

@RestController
@RequestMapping("/arquivos")
public class ControllerArquivoAnexado {
	
	@PostMapping
	public DeferredResult<ArquivoDTO> upload(@RequestParam("files[]") MultipartFile[] files) {
		DeferredResult<ArquivoDTO> resultado = new DeferredResult<>();
		
		Thread thread = new Thread(new ArquivoStorageRunnable(files, resultado));
		thread.start();
		
		return resultado;
	}
	
}
