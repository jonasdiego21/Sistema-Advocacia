package br.com.jdrmservices.araujocafe.services;

import java.util.Optional;

//import static br.com.jdrmservices.araujocafe.util.Constantes.INFORMACOES_JA_CADASTRADAS;

//import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jdrmservices.araujocafe.model.ContaPagar;
import br.com.jdrmservices.araujocafe.repository.ContasPagar;
import br.com.jdrmservices.araujocafe.services.exceptions.FornecedorContaPagarJaCadastradoException;
import br.com.jdrmservices.araujocafe.util.Constantes;

@Service
public class ServiceContaPagar {

	@Autowired
	private ContasPagar contasPagar;
	
	public ContaPagar pesquisarPorCodigo(Long codigo) {	
		return contasPagar.findOne(codigo);
	}

	@Transactional
	public void salvar(ContaPagar contaPagar) {
		Optional<ContaPagar> contaPagarOptional = contasPagar.findByFornecedorIgnoreCase(contaPagar.getFornecedor());
		
		if(contaPagar.isNova() && contaPagarOptional.isPresent()) {
			throw new FornecedorContaPagarJaCadastradoException(Constantes.INFORMACOES_JA_CADASTRADAS);
		}
		
		contasPagar.saveAndFlush(contaPagar);
	}
	
	@Transactional
	public void excluir(ContaPagar contaPagar) {
		contasPagar.delete(contaPagar);
	}
	
}
