package br.com.jdrmservices.araujocafe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jdrmservices.araujocafe.model.Processo;
import br.com.jdrmservices.araujocafe.model.constants.StatusProcesso;
import br.com.jdrmservices.araujocafe.repository.helper.processo.ProcessosQueries;

@Repository
public interface Processos extends JpaRepository<Processo, Long>, ProcessosQueries {
	public Processo findOne(Long codigo);
	public Optional<Processo> findByNumeroProcesso(String numeroProcesso);
	public List<Processo> findByStatus(StatusProcesso status);
}
