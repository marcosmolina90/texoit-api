package br.com.molina.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.molina.controller.dto.ResultDto;
import br.com.molina.model.Filme;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
	
	
	

}
