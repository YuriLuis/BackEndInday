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

import com.Inday.indaybackend.classes.Categoria;
import com.Inday.indaybackend.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaRepository repository;
	
	@GetMapping
	public List<Categoria> findAll(){
		
		return repository.findAll();
	}
	
	@PostMapping
	public Categoria salvar(@RequestBody @Valid Categoria categoria) {
		
		return repository.save(categoria);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> find(@PathVariable("id") Integer id){
		
		Optional<Categoria> categoria = repository.findById(id);
		
		if (categoria.isPresent()) {
			
			return ResponseEntity.ok(categoria.get());		
		}
		
		return ResponseEntity.notFound().build();
	}

}
