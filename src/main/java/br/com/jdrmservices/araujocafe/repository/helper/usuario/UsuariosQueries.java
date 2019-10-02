package br.com.jdrmservices.araujocafe.repository.helper.usuario;
	
import java.util.List;

import br.com.jdrmservices.araujocafe.model.Usuario;
	
public interface UsuariosQueries {	
	public List<String> permissoes(Usuario usuario);
}