package br.com.jdrmservices.araujocafe.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import br.com.jdrmservices.araujocafe.exception.NegocioException;
import br.com.jdrmservices.araujocafe.model.Usuario;
import br.com.jdrmservices.araujocafe.repository.Usuarios;
import br.com.jdrmservices.araujocafe.util.Constantes;

@Service
public class ServiceUsuario {
	
	@Autowired
	private Usuarios usuarios;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void salvar(Usuario usuario) {
		
		Optional<Usuario> optional = usuarios.findByEmail(usuario.getEmail());
		
		if (usuario.isNovo() && optional.isPresent()) {
			throw new NegocioException(Constantes.VIEW_CADASTRO_USUARIO);
		}
		
		if (usuario.isNovo() && StringUtils.isEmpty(usuario.getSenha())) {
			throw new NegocioException(Constantes.USUARIO_SENHA_OBRIGATORIA);
		}
		
		if (usuario.isNovo()) {
			usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));
		}
		
		usuarios.save(usuario);
		
	}
}
