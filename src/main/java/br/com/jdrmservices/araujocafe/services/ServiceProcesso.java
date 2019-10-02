package br.com.jdrmservices.araujocafe.services;

import static br.com.jdrmservices.araujocafe.util.Constantes.INFORMACOES_JA_CADASTRADAS;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jdrmservices.araujocafe.model.Processo;
import br.com.jdrmservices.araujocafe.repository.Processos;
import br.com.jdrmservices.araujocafe.services.exceptions.NumeroProcessoJaCadastradoException;

@Service
public class ServiceProcesso {

	@Autowired
	private Processos processos;
	
	public Processo pesquisarPorCodigo(Long codigo) {	
		return processos.findOne(codigo);
	}

	@Transactional
	public void salvar(Processo processo) {
		Optional<Processo> processoOptional = processos.findByNumeroProcesso(processo.getNumeroProcesso());
		
		if(processoOptional.isPresent()) {
			throw new NumeroProcessoJaCadastradoException(INFORMACOES_JA_CADASTRADAS);
		}
		
		processos.saveAndFlush(processo);
	}
	
	@Transactional
	public void excluir(Processo processo) {
		processos.delete(processo);
	}
}
