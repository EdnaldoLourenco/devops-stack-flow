package com.williamledo.tds.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.williamledo.tds.models.Encurtador;
import com.williamledo.tds.repositories.EncurtadorRepository;
import com.williamledo.tds.services.EncurtadorService;

@SpringBootTest
@AutoConfigureMockMvc
public class EncurtadorControllerTest {

	private static final Long id = 1L;
	private static final String codigo= "a231c";
	private static final String urlEncurtada = "localhost:8080/a231c";
	private static final String urlOriginal = "http://www.google.com";
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	EncurtadorService service;
	
	
	@Test
	void findAll() throws Exception {
		
		Encurtador encurtador = new Encurtador(id, codigo, urlEncurtada, urlOriginal);
		
		Mockito.when(service.findAll()).thenReturn(List.of(encurtador));
		this.mockMvc.perform(get("/encurtadores"))
			.andExpect(status().isOk())
			.andExpect(content().json("[{\"urlEncurtada\":\"localhost:8080/a231c\",\"qtdDeAcesso\":0,\"urlOriginal\":\"http://www.google.com\"}]"));
			
	}
	
}
