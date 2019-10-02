package br.com.jdrmservices.araujocafe.repository.helper.contaReceberLancamento;

import java.math.BigDecimal;
import java.time.MonthDay;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ContasReceberLancamentoImpl implements ContasReceberLancamentoQueries {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public BigDecimal valorTotalRecebidoNoMes() {
		Optional<BigDecimal> optional = Optional.ofNullable(entityManager
				.createQuery("select sum(valorRecebido) from ContaReceberLancamento where month(dataRecebimento) = :mes", BigDecimal.class)
				.setParameter("mes", MonthDay.now().getMonthValue())
				.getSingleResult());
		return optional.orElse(BigDecimal.ZERO);
	}
	
}
