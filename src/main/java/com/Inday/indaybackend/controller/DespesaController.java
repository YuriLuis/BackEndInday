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

import com.Inday.indaybackend.classes.Despesa;
import com.Inday.indaybackend.classes.Receita;
import com.Inday.indaybackend.repository.DespesaRepository;
import com.Inday.indaybackend.repository.ReceitaRepository;

@RestController
@RequestMapping("/despesa")
@CrossOrigin("*")
public class DespesaController {
	
	@Autowired
	private DespesaRepository repository;
	
	@Autowired
	private ReceitaRepository receitaRepository;
	
	@GetMapping
	public List<Despesa> findAll(){
		
		return repository.findAll();
	}
	
	@GetMapping("/saldo")
	public Double saldo() {
		
		Double despesa = totalDespesa();
		Double receita = totalReceita();
		
		Double resultado = receita + despesa;
		
		return resultado;
	}
	
	public Double totalDespesa() {
		
		Double soma = 0.0;
		
		for (Despesa d : repository.findAll()) {
			
			soma += d.getValor();
		}
		
		return soma;
	}
	
	public Double totalReceita() {
		
		Double soma = 0.0;
		
		for (Receita r : receitaRepository.findAll()) {
			
			soma += r.getValor();
		}
		
		return soma;
	}
	
	@PostMapping("/cadastrar")
	@CrossOrigin("*")
	public ResponseEntity<?> salvar(@RequestBody @Valid  Despesa despesa) {	
		
		if (repository.findByDescricaoAndValor(despesa.getDescricao(), despesa.getValor()) == null) {
			
			return  ResponseEntity.ok(repository.save(despesa));
			
		}else {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				   .body(String.format("Despesa já resgitrada! "));
		}	
	}
		
	
	@GetMapping("/{id}")
	public ResponseEntity<?> find(@PathVariable("id") Integer id){
		
		Optional<Despesa> despesa = repository.findById(id);
		
		if (despesa.isPresent()) {
			
			despesa.get().getLogin().setLogado(true);
			return ResponseEntity.ok(despesa.get());		
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
