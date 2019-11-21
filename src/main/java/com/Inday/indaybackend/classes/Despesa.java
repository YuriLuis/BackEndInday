package com.Inday.indaybackend.classes;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table
public class Despesa implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	private String descricao;

	@NotNull
	private Double valor;

	private LocalDate date;

	@ManyToOne
	@JoinColumn
	private Categoria categoria;

	@ManyToOne
	@JoinColumn
	private Login login;
	
	
	private Boolean tipoDespesa;

	public Despesa() {
		
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		
		if (valor > 0) {
			this.valor -= valor;
		}else {
			
			throw new RuntimeException("Valor negativo!");
		}
		
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public Integer getId() {
		return id;
	}

	public Boolean getTipoDespesa() {
		return tipoDespesa;
	}

}
