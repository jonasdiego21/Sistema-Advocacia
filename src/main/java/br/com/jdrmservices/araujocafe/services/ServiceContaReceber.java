package br.com.jdrmservices.araujocafe.services;

import static br.com.jdrmservices.araujocafe.util.Constantes.INFORMACOES_JA_CADASTRADAS;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jdrmservices.araujocafe.model.ContaReceber;
import br.com.jdrmservices.araujocafe.repository.ContasReceber;
import br.com.jdrmservices.araujocafe.services.exceptions.NumeroProcessoContaReceberJaCadastradoException;

@Service
public class ServiceContaReceber {

	@Autowired
	private ContasReceber contasReceber;
	
	public ContaReceber pesquisarPorCodigo(Long codigo) {	
		return contasReceber.findOne(codigo);
	}
	
	public List<ContaReceber> listar() {
		List<ContaReceber> lista = contasReceber.findAll();
		
		return lista;
	}
	

	@Transactional
	public void salvar(ContaReceber contaReceber) {

		Optional<ContaReceber> contaReceberOptional = contasReceber.findByNumeroProcesso(contaReceber.getNumeroProcesso());
		
		if(contaReceber.isNova() && contaReceberOptional.isPresent()) {
			throw new NumeroProcessoContaReceberJaCadastradoException(INFORMACOES_JA_CADASTRADAS);
		}

		contasReceber.saveAndFlush(contaReceber);
	}
	
	@Transactional
	public void excluir(ContaReceber contaReceber) {
		contasReceber.delete(contaReceber);
	}
	
}
