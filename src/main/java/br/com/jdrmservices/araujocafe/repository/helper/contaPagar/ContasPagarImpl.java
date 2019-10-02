package br.com.jdrmservices.araujocafe.repository.helper.contaPagar;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.jdrmservices.araujocafe.dto.TotalDespesasNoAno;
import br.com.jdrmservices.araujocafe.dto.TotalDespesasNoMes;

public class ContasPagarImpl implements ContasPagarQueries {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public BigDecimal valorTotalDespesasNoMes() {
		Optional<BigDecimal> optional = Optional.ofNullable(entityManager
				.createQuery("select sum(valorTotal) from ContaPagar where month(dataVencimento) = :mes", BigDecimal.class)
				.setParameter("mes", MonthDay.now().getMonthValue())
				.getSingleResult());
		return optional.orElse(BigDecimal.ZERO);
	}

	@Override
	public BigDecimal valorTotalDespesasHoje() {
		Optional<BigDecimal> optional = Optional.ofNullable(entityManager
				.createQuery("select sum(valorTotal) from ContaPagar where day(dataVencimento) = :data", BigDecimal.class)
				.setParameter("data", LocalDate.now().getDayOfMonth())
				.getSingleResult());
		return optional.orElse(BigDecimal.ZERO);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TotalDespesasNoMes> totalDespesasNoMes() {
		return entityManager.createNamedQuery("Advocacia.totalDespesasNoMes").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TotalDespesasNoAno> totalDespesasNoAno() {
		return entityManager.createNamedQuery("Advocacia.totalDespesasNoAno").getResultList();
	}
	
}
