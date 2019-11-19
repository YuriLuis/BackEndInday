package com.Inday.indaybackend.controller;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	public Login salvar(@RequestBody @Valid Login login) {
	
		return repository.save(login);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> find(@PathVariable("id") Integer id){
		
		Optional<Login> login = repository.findById(id);
		
		if (login.isPresent()) {
			
			return ResponseEntity.ok(login.get());		
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
}
