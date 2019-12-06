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
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@NotNull
	private String descricao;

	@NotNull
	private double valor;

	@NotNull
	private LocalDate date;

	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;

	@ManyToOne
	@JoinColumn(name = "id_login")
	private Login login;
	
	private Boolean despesaFixa;
	
	private Boolean despesaUnica;
	
	private Boolean pago;

	public Despesa() {
		
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		
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
		return despesaFixa;
	}
	
	public void setTipoDespesa(Boolean despesaFixa) {
		
		this.despesaFixa = despesaFixa;
	}

	public Boolean getDespesaUnica() {
		return despesaUnica;
	}

	public void setDespesaUnica(Boolean despesaUnica) {
		this.despesaUnica = despesaUnica;
	}

	public Boolean getPago() {
		return pago;
	}

	public void setPago(Boolean pago) {
		this.pago = pago;
	}

}
