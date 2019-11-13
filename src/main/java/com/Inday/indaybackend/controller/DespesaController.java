package com.Inday.indaybackend.controller;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Inday.indaybackend.classes.Despesa;
import com.Inday.indaybackend.repository.DespesaRepository;

@RestController
@RequestMapping("/despesa")
public class DespesaController {
	
	@Autowired
	private DespesaRepository repository;
	
	@GetMapping
	public List<Despesa> findAll(){
		
		return repository.findAll();
	}
	
	@PostMapping
	public Despesa salvar(@RequestBody @Valid Despesa despesa) {
		
		return repository.save(despesa);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> find(@PathVariable("id") Integer id){
		
		Optional<Despesa> despesa = repository.findById(id);
		
		if (despesa.isPresent()) {
			
			return ResponseEntity.ok(despesa.get());		
		}
		
		return ResponseEntity.notFound().build();
	}


}
