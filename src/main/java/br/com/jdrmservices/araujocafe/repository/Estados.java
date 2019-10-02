package br.com.jdrmservices.araujocafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jdrmservices.araujocafe.model.Estado;

@Repository
public interface Estados extends JpaRepository<Estado, Long> {
	
}