package br.com.jdrmservices.araujocafe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jdrmservices.araujocafe.exception.NegocioException;
import br.com.jdrmservices.araujocafe.model.Despesa;
import br.com.jdrmservices.araujocafe.repository.Despesas;
import br.com.jdrmservices.araujocafe.util.Constantes;

@Service
public class ServiceDespesa {

	@Autowired
	private Despesas despesas;
	
	public Despesa salvar(Despesa despesa) {
		return this.despesas.saveAndFlush(despesa);
	}
	
	public void excluir(Despesa despesa) {	
		try {
			despesas.delete(despesa);
		} catch (Exception e) {
			throw new NegocioException(Constantes.ERRO_EXCLUIR_INFORMACOES);
		}	
	}	
}
