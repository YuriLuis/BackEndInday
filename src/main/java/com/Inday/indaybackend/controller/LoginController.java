package com.Inday.indaybackend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Inday.indaybackend.classes.Categoria;
import com.Inday.indaybackend.classes.Login;
import com.Inday.indaybackend.repository.LoginRepository;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private LoginRepository repository;

	@GetMapping
	public List<Login> findAll() {

		return repository.findAll();
	}

	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody @Valid Login login) {

		if (repository.findByUsuarioAndSenhaAndEmail(login.getUsuario(), login.getSenha(), login.getEmail()) == null) {

			return ResponseEntity.ok(repository.save(login));

		} else {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(String.format("--->Usuario já Cadastrada! <---"));
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> find(@PathVariable("id") Integer id) {

		Optional<Login> login = repository.findById(id);

		if (login.isPresent()) {

			return ResponseEntity.ok(login.get());
		}

		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("{id}")
	public void deletePorId(@PathVariable("id") Integer id) {

		if (repository.findById(id) != null) {
			
			repository.deleteById(id);
		}
	}
	
	@GetMapping("/autentica")
	public String autenticaLogin(@RequestBody Login login) {
		
		List<Login> aux = new ArrayList<Login>();
		
		String auxS = "";
		
		for (Login l : findAll()) {
			
			if (login.getUsuario().equalsIgnoreCase(l.getUsuario()) && (login.getSenha().equalsIgnoreCase(l.getSenha()))) {
				
				auxS = "Logado com sucesso!";
				
				return  auxS;
			
			}
		}
		
		auxS = "Login ou senha incorreto!";
		
		return auxS;
	}

}
