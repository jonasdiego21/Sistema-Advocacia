package br.com.jdrmservices.araujocafe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jdrmservices.araujocafe.model.Cidade;

@Repository
public interface Cidades extends JpaRepository<Cidade, Long> {	
	public List<Cidade> findByEstadoCodigo(Long codigo);	
}
