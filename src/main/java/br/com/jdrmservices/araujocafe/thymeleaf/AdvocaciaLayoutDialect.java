package br.com.jdrmservices.araujocafe.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import br.com.jdrmservices.araujocafe.thymeleaf.processor.CampoObrigatorioElementTagProcessor;
import br.com.jdrmservices.araujocafe.thymeleaf.processor.MessageElementTagProcessor;
import br.com.jdrmservices.araujocafe.thymeleaf.processor.NavegacaoElementTagProcessor;
import br.com.jdrmservices.araujocafe.thymeleaf.processor.PaginationElementTagProcessor;
import br.com.jdrmservices.araujocafe.thymeleaf.processor.PreLoaderElementTagProcessor;
import br.com.jdrmservices.araujocafe.thymeleaf.processor.TituloElementTagProcessor;

@Component
public class AdvocaciaLayoutDialect extends AbstractProcessorDialect {
	
	public AdvocaciaLayoutDialect() {
		super("Componente", "cmp", StandardDialect.PROCESSOR_PRECEDENCE);
	}
	
	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processadores = new HashSet<>();
		
		processadores.add(new CampoObrigatorioElementTagProcessor(dialectPrefix));
		processadores.add(new MessageElementTagProcessor(dialectPrefix));
		processadores.add(new PreLoaderElementTagProcessor(dialectPrefix));
		processadores.add(new NavegacaoElementTagProcessor(dialectPrefix));
		processadores.add(new TituloElementTagProcessor(dialectPrefix));
		processadores.add(new PaginationElementTagProcessor(dialectPrefix));
		
		return processadores;
	}
	
}