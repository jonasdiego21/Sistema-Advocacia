package br.com.jdrmservices.araujocafe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jdrmservices.araujocafe.model.Cliente;
import br.com.jdrmservices.araujocafe.repository.helper.cliente.ClientesQueries;

@Repository
public interface Clientes extends JpaRepository<Cliente, Long>, ClientesQueries {
	public Optional<Cliente> findByNomeIgnoreCase(String nome);
	public Cliente findOne(Long codigo);
}
