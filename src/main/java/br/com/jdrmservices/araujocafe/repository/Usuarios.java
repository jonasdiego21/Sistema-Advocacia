package br.com.jdrmservices.araujocafe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jdrmservices.araujocafe.model.Usuario;
import br.com.jdrmservices.araujocafe.repository.helper.usuario.UsuariosQueries;

@Repository
public interface Usuarios extends JpaRepository<Usuario, Long>, UsuariosQueries {
	public Optional<Usuario> findByNome(String nome);
	public Optional<Usuario> findByEmailIgnoreCaseAndAtivoTrue(String email);
	public Optional<Usuario> findByEmail(String email);
}
