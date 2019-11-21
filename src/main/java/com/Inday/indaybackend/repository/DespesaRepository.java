package com.Inday.indaybackend.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Inday.indaybackend.classes.Despesa;

public interface DespesaRepository  extends JpaRepository<Despesa, Integer> {

	public Despesa findByDescricaoAndValor(String descricao, Double valor);
}
