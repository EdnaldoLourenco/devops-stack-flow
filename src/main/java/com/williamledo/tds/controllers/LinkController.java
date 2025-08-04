package com.williamledo.tds.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.williamledo.tds.services.EncurtadorService;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping
public class LinkController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private EncurtadorService service;
	
	@GetMapping("/{codigo}")
	public void redirect(@PathVariable String codigo, HttpServletResponse response) throws IOException {
		
		
			var encurtador = service.findByCodigo(codigo);
			service.addAcesso(encurtador.getId());
			service.salvar(encurtador);
			
			response.sendRedirect(encurtador.getUrlOriginal());
		
	}
	
}
