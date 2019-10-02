package br.com.jdrmservices.araujocafe.repository.helper.contaPagarLancamento;

import java.math.BigDecimal;
import java.time.MonthDay;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ContasPagarLancamentoImpl implements ContasPagarLancamentoQueries {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public BigDecimal totalPagoNoMes() {
		Optional<BigDecimal> optional = Optional.ofNullable(entityManager
				.createQuery("select sum(valorPago) from ContaPagarLancamento where month(dataPagamento) = :mes", BigDecimal.class)
				.setParameter("mes", MonthDay.now().getMonthValue())
				.getSingleResult());
		return optional.orElse(BigDecimal.ZERO);
	}
}
