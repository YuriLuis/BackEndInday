package com.Inday.indaybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Inday.indaybackend.classes.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria,Integer > {

	
	public Categoria findByDescricao(String descricao);
	
	
}
