package br.com.jdrmservices.araujocafe.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jdrmservices.araujocafe.model.ContaReceberLancamento;
import br.com.jdrmservices.araujocafe.repository.ContasReceberLancamento;
import br.com.jdrmservices.araujocafe.services.exceptions.ContaReceberLancamentoException;
import br.com.jdrmservices.araujocafe.util.Constantes;

@Service
public class ServiceContaReceberLancamento {

	@Autowired
	private ContasReceberLancamento contasReceberLancamento;
	
	public void salvar(ContaReceberLancamento contaReceberLancamento) {
		Optional<ContaReceberLancamento> contaReceberLancamentoOptional = contasReceberLancamento.findByCodigo(contaReceberLancamento.getCodigo());
		
		if(contaReceberLancamento.isNova() && contaReceberLancamentoOptional.isPresent()) {
			throw new ContaReceberLancamentoException(Constantes.INFORMACOES_JA_CADASTRADAS);
		}
		
		contasReceberLancamento.saveAndFlush(contaReceberLancamento);
	}
	
	public void excluir(ContaReceberLancamento contaReceberLancamento) {
		contasReceberLancamento.delete(contaReceberLancamento);
	}
	
	public List<ContaReceberLancamento> listar() {
		return contasReceberLancamento.findAll();
	}
	
}
