package br.com.jdrmservices.araujocafe.repository.helper.processo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.jdrmservices.araujocafe.dto.TotalProcessosPorStatus;
import br.com.jdrmservices.araujocafe.model.Processo;
import br.com.jdrmservices.araujocafe.model.constants.StatusProcesso;
import br.com.jdrmservices.araujocafe.repository.filter.ProcessoFilter;

public interface ProcessosQueries {
	public Page<Processo> filtrar(Pageable pageable);
	public List<Processo> filtrar(ProcessoFilter processoFilter);
	public List<TotalProcessosPorStatus> totalProcessosPorStatus(StatusProcesso status);
}
