package br.com.jdrmservices.araujocafe.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jdrmservices.araujocafe.model.Agenda;
import br.com.jdrmservices.araujocafe.repository.helper.agenda.AgendaQueries;

@Repository
public interface Agendas extends JpaRepository<Agenda, Long>, AgendaQueries {
	public Agenda findOne(Long codigo);
	public Optional<Agenda> findByTituloIgnoreCase(String titulo);
	public List<Agenda> findByDataCompromisso(LocalDate data);
}
