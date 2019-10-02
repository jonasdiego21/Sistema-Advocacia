package br.com.jdrmservices.araujocafe.services;

import static br.com.jdrmservices.araujocafe.util.Constantes.INFORMACOES_JA_CADASTRADAS;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jdrmservices.araujocafe.model.Agenda;
import br.com.jdrmservices.araujocafe.repository.Agendas;
import br.com.jdrmservices.araujocafe.services.exceptions.TituloAgendaJaCadastradoException;

@Service
public class ServiceAgenda {
	
	@Autowired
	private Agendas agendas;
	
	public Agenda pesquisarPorCodigo(Long codigo) {	
		return agendas.findOne(codigo);
	}
	
	public List<Agenda> listar() {
		List<Agenda> lista = agendas.findAll();
		
		return lista;
	}
	

	@Transactional
	public void salvar(Agenda agenda) {

		Optional<Agenda> agendaOptional = agendas.findByTituloIgnoreCase(agenda.getTitulo());
		
		if(agenda.isNova() && agendaOptional.isPresent()) {
			throw new TituloAgendaJaCadastradoException(INFORMACOES_JA_CADASTRADAS);
		}

		agendas.saveAndFlush(agenda);
	}
	
	@Transactional
	public void excluir(Agenda agenda) {
		agendas.delete(agenda);
	}
}
