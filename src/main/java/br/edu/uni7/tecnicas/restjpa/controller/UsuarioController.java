package br.edu.uni7.tecnicas.restjpa.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.uni7.tecnicas.restjpa.business.UsuarioBusiness;
import br.edu.uni7.tecnicas.restjpa.entity.Usuario;

@RestController
public class UsuarioController {
	@Autowired
	private UsuarioBusiness business;

	@GetMapping(path = "/usuarios")
	public ResponseEntity<List<Usuario>> buscarTodos() {
		ResponseEntity<List<Usuario>> response = new ResponseEntity<List<Usuario>>(HttpStatus.NO_CONTENT);

		List<Usuario> usuarios = business.buscarTodos();
		if (!usuarios.isEmpty()) {
			response = ResponseEntity.ok(usuarios);
		}

		return response;
	}

	@GetMapping(path = "/usuarios/{id}")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable Integer id) {
		ResponseEntity<Usuario> response = 
				new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);

		Usuario usuario = business.buscarPorId(id);
		if (usuario != null) {
			response = ResponseEntity.ok(usuario);
		}

		return response;
	}

	@PostMapping(path = "/usuarios")
	public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
		business.salvar(usuario);

		return new ResponseEntity<Usuario>(HttpStatus.CREATED);
	}
	
	@DeleteMapping(path = "/usuarios/purge")
	public ResponseEntity<Usuario> removerTodos() {
		business.removerTodos();

		return new ResponseEntity<Usuario>(HttpStatus.OK);
	}
	
	@PostMapping(path = "/autenticar")
	public Boolean autenticar(@RequestParam String login, @RequestParam String senha) {
		return business.autenticar(login, senha);
	}
}
