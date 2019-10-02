package br.com.jdrmservices.araujocafe.repository.helper.usuario;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.jdrmservices.araujocafe.model.Usuario;

public class UsuariosImpl implements UsuariosQueries {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<String> permissoes(Usuario usuario) {
		
		String jpql = "select distinct p.nome from Usuario u inner join u.grupo g inner join g.permissoes p where u = :usuario";
		
		List<String> permissoes = entityManager.createQuery(jpql, String.class)
					.setParameter("usuario", usuario)
					.getResultList();
		
		return permissoes;
	}
	
}