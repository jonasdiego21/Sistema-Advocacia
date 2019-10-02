package br.com.jdrmservices.araujocafe.storage;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import br.com.jdrmservices.araujocafe.dto.ArquivoDTO;

public class ArquivoStorageRunnable implements Runnable {

	private MultipartFile[] files;
	private DeferredResult<ArquivoDTO> resultado;
	
	public ArquivoStorageRunnable(MultipartFile[] files, DeferredResult<ArquivoDTO> resultado) {
		this.files = files;
		this.resultado = resultado;
	}
	
	@Override
	public void run() {
		String nomeDocumento = files[0].getOriginalFilename();
		String contentType = files[0].getContentType();
		
		resultado.setResult(new ArquivoDTO(nomeDocumento, contentType));
	}

}
