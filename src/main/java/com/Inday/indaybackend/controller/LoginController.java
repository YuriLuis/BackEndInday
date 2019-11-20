package com.Inday.indaybackend.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	/*
	 * public Login salvar(@RequestBody @Valid Login login) {
	 * 
	 * return repository.save(login); }
	 * 
	 */
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

}
