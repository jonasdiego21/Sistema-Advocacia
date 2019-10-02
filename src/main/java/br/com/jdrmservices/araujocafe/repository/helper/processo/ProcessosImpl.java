package br.com.jdrmservices.araujocafe.repository.helper.processo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.jdrmservices.araujocafe.dto.TotalProcessosPorStatus;
import br.com.jdrmservices.araujocafe.model.Processo;
import br.com.jdrmservices.araujocafe.model.constants.StatusProcesso;
import br.com.jdrmservices.araujocafe.paginacao.PaginacaoUtil;
import br.com.jdrmservices.araujocafe.repository.filter.ProcessoFilter;

public class ProcessosImpl implements ProcessosQueries {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<Processo> filtrar(Pageable pageable) {	
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(Processo.class);	
		paginacaoUtil.preparar(criteria, pageable);	
		return new PageImpl<>(criteria.list(), pageable, total());
	}
	
	private Long total() {
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(Processo.class);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Processo> filtrar(ProcessoFilter filtro) {
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(Processo.class);
		
		if(filtro != null) {
			if(!StringUtils.isEmpty(filtro.getNumeroProcesso())) {
				criteria.add(Restrictions.ilike("numeroProcesso", filtro.getNumeroProcesso(), MatchMode.ANYWHERE));
			} if(!StringUtils.isEmpty(filtro.getCliente())) {
				criteria.add(Restrictions.eq("cliente", filtro.getCliente()));
			} if(!StringUtils.isEmpty(filtro.getNomeParteContraria())) {
				criteria.add(Restrictions.ilike("nomeParteContraria", filtro.getNomeParteContraria(), MatchMode.ANYWHERE));
			} if(!StringUtils.isEmpty(filtro.getStatus())) {
				criteria.add(Restrictions.eq("status", filtro.getStatus()));
			}
		}
		
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TotalProcessosPorStatus> totalProcessosPorStatus(StatusProcesso status) {		
		List<TotalProcessosPorStatus> listaTotalProcessoPorStatus  = entityManager
				.createNamedQuery("Advocacia.totalProcessosPorStatus")
				.setParameter("status", status)
				.getResultList();
		
		return listaTotalProcessoPorStatus;
	}
}
