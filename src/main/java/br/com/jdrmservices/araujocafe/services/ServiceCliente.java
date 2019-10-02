package br.com.jdrmservices.araujocafe.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jdrmservices.araujocafe.model.Cliente;
import br.com.jdrmservices.araujocafe.repository.Clientes;
import br.com.jdrmservices.araujocafe.services.exceptions.NomeClienteJaCadastradoException;

import static br.com.jdrmservices.araujocafe.util.Constantes.INFORMACOES_JA_CADASTRADAS;

@Service
public class ServiceCliente {

	@Autowired
	private Clientes clientes;
	
	public Cliente pesquisarPorCodigo(Long codigo) {	
		return clientes.findOne(codigo);
	}

	@Transactional
	public void salvar(Cliente cliente) {
		Optional<Cliente> clienteOptional = clientes.findByNomeIgnoreCase(cliente.getNome());
		
		if(cliente.isNovo() && clienteOptional.isPresent()) {
			throw new NomeClienteJaCadastradoException(INFORMACOES_JA_CADASTRADAS);
		}
		
		clientes.saveAndFlush(cliente);
	}
	
	@Transactional
	public void excluir(Cliente cliente) {
		clientes.delete(cliente);
	}
}
