package br.com.jdrmservices.araujocafe.repository;

import java.util.Optional;

//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jdrmservices.araujocafe.model.DocumentoApresentado;

@Repository
public interface DocumentosApresentados extends JpaRepository<DocumentoApresentado, Long> {
	Optional<DocumentoApresentado> findByNome(String nome);
	//Page<DocumentoApresentado> filtrar(Pageable pageable);
}
