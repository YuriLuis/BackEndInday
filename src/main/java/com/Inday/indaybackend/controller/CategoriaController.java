package com.Inday.indaybackend.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@CrossOrigin("*")
public class CategoriaController {
	
	

	@Autowired
	private CategoriaRepository repository;
	
	@GetMapping
	public List<Categoria> findAll(){
		
		return repository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody  Categoria categoria) {	
		
		if (repository.findByDescricao(categoria.getDescricao()) == null) {
			
			return  ResponseEntity.ok(repository.save(categoria));
			
		}else {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				   .body(String.format("--->Categoria já Cadastrada! <---"));
		}	
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> find(@PathVariable("id") Integer id){
		
		Optional<Categoria> categoria = repository.findById(id);
		
		if (categoria.isPresent()) {
			
			return ResponseEntity.ok(categoria.get());		
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
