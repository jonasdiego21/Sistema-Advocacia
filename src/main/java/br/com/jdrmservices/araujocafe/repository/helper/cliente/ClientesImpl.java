package br.com.jdrmservices.araujocafe.repository.helper.cliente;

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

import br.com.jdrmservices.araujocafe.model.Cliente;
import br.com.jdrmservices.araujocafe.paginacao.PaginacaoUtil;
import br.com.jdrmservices.araujocafe.repository.filter.ClienteFilter;

public class ClientesImpl implements ClientesQueries {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<Cliente> filtrar(Pageable pageable) {		
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(Cliente.class);		
		paginacaoUtil.preparar(criteria, pageable);		
		return new PageImpl<>(criteria.list(), pageable, total());
	}
	
	private Long total() {
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(Cliente.class);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> filtrar(ClienteFilter filtro) {
		Criteria criteria = entityManager.unwrap(Session.class).createCriteria(Cliente.class);
		
		if(filtro != null) {
			if(!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			} else if(!StringUtils.isEmpty(filtro.getCpf())) {
				criteria.add(Restrictions.ilike("cpf", filtro.getCpf(), MatchMode.ANYWHERE));
			}
		}
		
		return criteria.list();
	}
}
