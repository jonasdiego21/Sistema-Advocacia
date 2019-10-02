package br.com.jdrmservices.araujocafe.storage;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import br.com.jdrmservices.araujocafe.dto.DocumentoDTO;

public class DocumentoStorageRunnable implements Runnable {

	private MultipartFile[] files;
	private DeferredResult<DocumentoDTO> resultado;
	
	public DocumentoStorageRunnable(MultipartFile[] files, DeferredResult<DocumentoDTO> resultado) {
		this.files = files;
		this.resultado = resultado;
	}
	
	@Override
	public void run() {
		String nomeDocumento = files[0].getOriginalFilename();
		String contentType = files[0].getContentType();
		
		resultado.setResult(new DocumentoDTO(nomeDocumento, contentType));
	}

}
