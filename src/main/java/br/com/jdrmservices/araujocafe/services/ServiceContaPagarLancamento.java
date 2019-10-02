package br.com.jdrmservices.araujocafe.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jdrmservices.araujocafe.model.ContaPagarLancamento;
import br.com.jdrmservices.araujocafe.repository.ContasPagarLancamento;
import br.com.jdrmservices.araujocafe.services.exceptions.ContaPagarLancamentoException;
import br.com.jdrmservices.araujocafe.util.Constantes;

@Service
public class ServiceContaPagarLancamento {

	@Autowired
	private ContasPagarLancamento contasPagarLancamento;
	
	public void salvar(ContaPagarLancamento contaPagarLancamento) {
		Optional<ContaPagarLancamento> contaPagarLancamentoOptional = contasPagarLancamento.findByCodigo(contaPagarLancamento.getCodigo());
		
		if(contaPagarLancamento.isNova() && contaPagarLancamentoOptional.isPresent()) {
			throw new ContaPagarLancamentoException(Constantes.INFORMACOES_JA_CADASTRADAS);
		}
		
		contasPagarLancamento.saveAndFlush(contaPagarLancamento);
	}
	
	public void excluir(ContaPagarLancamento contaPagarLancamento) {
		contasPagarLancamento.delete(contaPagarLancamento);
	}
	
	public List<ContaPagarLancamento> listar() {
		return contasPagarLancamento.findAll();
	}
}
