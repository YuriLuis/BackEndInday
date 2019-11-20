package com.Inday.indaybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Inday.indaybackend.classes.Login;

public interface LoginRepository extends JpaRepository<Login, Integer>{

	public Login findByUsuarioAndSenhaAndEmail(String usuario, String senha, String email);
	
	
}
