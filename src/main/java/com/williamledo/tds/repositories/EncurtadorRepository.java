package com.williamledo.tds.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.williamledo.tds.models.Encurtador;

import jakarta.transaction.Transactional;

public interface EncurtadorRepository extends JpaRepository<Encurtador, Long> {

	Encurtador findByUrlOriginal(String urlOriginal);
	
	Encurtador findByCodigo(String codigo);

	@Modifying
	@Transactional
	@Query("update Encurtador set qtdDeAcessos = qtdDeAcessos + 1 where id = :id")
	void addAcesso(Long id);
	
}
