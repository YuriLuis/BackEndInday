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
public class Receita implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@NotNull
	private String descricao;

	@NotNull
	private Double valor;

	@NotNull
	private LocalDate date;

	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;

	@ManyToOne
	@JoinColumn(name = "id_login")
	private Login login;
	
	private Boolean receitaFixa;
	
	private Boolean receitaUnica;
	
	private Boolean pago;

	public Receita() {

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
		this.valor = valor;
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

	public Boolean getReceitaFixa() {
		return receitaFixa;
	}
	
	public void setReceitaFixa(Boolean receitaFixa) {
		
		this.receitaFixa = receitaFixa;
	}

	public Boolean getReceitaUnica() {
		return receitaUnica;
	}

	public void setReceitaUnica(Boolean receitaUnica) {
		this.receitaUnica = receitaUnica;
	}

	public Boolean getPago() {
		return pago;
	}

	public void setPago(Boolean pago) {
		this.pago = pago;
	}
	

}
