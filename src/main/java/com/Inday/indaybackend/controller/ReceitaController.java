package com.Inday.indaybackend.controller;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Inday.indaybackend.classes.Receita;
import com.Inday.indaybackend.repository.ReceitaRepository;

@RestController
@RequestMapping("/receita")
@CrossOrigin("*")
public class ReceitaController {

	@Autowired
	private ReceitaRepository repository;
	
	@GetMapping
	public List<Receita> findAll(){
		
		return repository.findAll();
	}
	
	@PostMapping
	public Receita salvar(@RequestBody @Valid Receita receita) {
		
		return repository.save(receita);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> find(@PathVariable("id") Integer id){
		
		Optional<Receita> receita = repository.findById(id);
		
		if (receita.isPresent()) {
			
			return ResponseEntity.ok(receita.get());		
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("{id}")
	public void deletePorId(@PathVariable("id") Integer id) {

		if (repository.findById(id) != null) {
			
			repository.deleteById(id);
		}
	}
}

