package com.williamledo.tds.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.williamledo.tds.DTO.CadastroUrlDTO;
import com.williamledo.tds.DTO.DetalhamentoEncurtadorDTO;
import com.williamledo.tds.DTO.EstatisticasDTO;
import com.williamledo.tds.models.Encurtador;
import com.williamledo.tds.services.EncurtadorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/encurtadores")
public class EncurtadorController {

	@Autowired
	private EncurtadorService service;
	
	@PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid CadastroUrlDTO dados, UriComponentsBuilder uriBuilder) {
    	
		service.criar(dados.url());
		
		var encurtador = service.findByUrlOriginal(dados.url());
		
		var uri = uriBuilder.path("/encurtadores/{id}").buildAndExpand(encurtador.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DetalhamentoEncurtadorDTO(encurtador));
		
    }
	
	@GetMapping
	public ResponseEntity <List<EstatisticasDTO>> findAll() {
		
		return ResponseEntity.ok().body(service.findAll().stream().map(EstatisticasDTO::new).toList());
		
	}
	
	
}
