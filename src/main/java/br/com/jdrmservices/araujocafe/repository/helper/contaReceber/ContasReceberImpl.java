package br.com.jdrmservices.araujocafe.repository.helper.contaReceber;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.jdrmservices.araujocafe.dto.TotalReceitasNoAno;
import br.com.jdrmservices.araujocafe.dto.TotalReceitasNoMes;

public class ContasReceberImpl implements ContasReceberQueries {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public BigDecimal valorTotalReceitasNoMes() {
		Optional<BigDecimal> optional = Optional.ofNullable(entityManager
				.createQuery("select sum(valor) from ContaReceber where month(dataVencimento) = :mes", BigDecimal.class)
				.setParameter("mes", MonthDay.now().getMonthValue())
				.getSingleResult());
		return optional.orElse(BigDecimal.ZERO);
	}

	@Override
	public BigDecimal valorTotalReceitasHoje() {
		Optional<BigDecimal> optional = Optional.ofNullable(entityManager
				.createQuery("select sum(valor) from ContaReceber where day(dataVencimento) = :data", BigDecimal.class)
				.setParameter("data", LocalDate.now().getDayOfMonth())
				.getSingleResult());
		return optional.orElse(BigDecimal.ZERO);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TotalReceitasNoMes> totalReceitasNoMes() {
		return entityManager.createNamedQuery("Advocacia.totalReceitasNoMes").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TotalReceitasNoAno> totalReceitasNoAno() {
		return entityManager.createNamedQuery("Advocacia.totalReceitasNoAno").getResultList();
	}
	
}
