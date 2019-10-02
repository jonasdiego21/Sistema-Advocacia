package br.com.jdrmservices.araujocafe.repository.helper.cliente;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.jdrmservices.araujocafe.model.Cliente;
import br.com.jdrmservices.araujocafe.repository.filter.ClienteFilter;

public interface ClientesQueries {
	public Page<Cliente> filtrar(Pageable pageable);
	public List<Cliente> filtrar(ClienteFilter clienteFilter);
}
