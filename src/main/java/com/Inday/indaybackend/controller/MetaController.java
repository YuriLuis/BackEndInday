package com.Inday.indaybackend.controller;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Inday.indaybackend.classes.Meta;
import com.Inday.indaybackend.repository.MetaRepository;

@RestController
@RequestMapping("/meta")
public class MetaController {
	
	@Autowired
	private MetaRepository repository;
	
	@GetMapping
	public List<Meta> findAll(){
		
		return repository.findAll();
	}
	
	@PostMapping
	public Meta salvar(@RequestBody @Valid Meta meta) {
		
		return repository.save(meta);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> find(@PathVariable("id") Integer id){
		
		Optional<Meta> meta = repository.findById(id);
		
		if (meta.isPresent()) {
			
			return ResponseEntity.ok(meta.get());		
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
