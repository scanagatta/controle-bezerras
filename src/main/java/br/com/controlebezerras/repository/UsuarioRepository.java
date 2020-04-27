package br.com.controlebezerras.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.controlebezerras.model.Usuario;



public interface UsuarioRepository extends CrudRepository<Usuario, String>{

	Usuario findByLogin(String login);
}
