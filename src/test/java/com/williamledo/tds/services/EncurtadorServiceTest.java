package com.williamledo.tds.services;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.williamledo.tds.models.Encurtador;
import com.williamledo.tds.repositories.EncurtadorRepository;

@SpringBootTest
public class EncurtadorServiceTest {

	private static final Long id = 1L;
	private static final String codigo= "a231c";
	private static final String urlEncurtada = "localhost:8080/a231c";
	private static final String urlOriginal = "http://www.google.com";
	
	@InjectMocks
	private EncurtadorService service;
	
	@Mock
	private EncurtadorRepository repository;
	
	private Encurtador encurtador;
	
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startEncurtador();
	}
	
	@Test
	void whenFindAllThenReturnAnListOfEncurtadores() {
		
		Mockito.when(repository.findAll()).thenReturn(List.of(encurtador));
		
		List<Encurtador> response = service.findAll();
		
		Assertions.assertNotNull(response);
		Assertions.assertEquals(1, response.size());
		Assertions.assertEquals(Encurtador.class, response.get(0).getClass());
		
		Assertions.assertEquals(id, response.get(0).getId());
		Assertions.assertEquals(codigo, response.get(0).getCodigo());
		Assertions.assertEquals(urlEncurtada, response.get(0).getUrlEncurtada());
		Assertions.assertEquals(urlOriginal, response.get(0).getUrlOriginal());
		
	}
	
	@Test
	void whenFindByUrlOriginalThenReturnAnEncurtadorInstance() {
		
		Mockito.when(repository.findByUrlOriginal(Mockito.anyString())).thenReturn(encurtador);
		
		Encurtador response = service.findByUrlOriginal(urlOriginal);
		
		Assertions.assertNotNull(response);
		Assertions.assertEquals(Encurtador.class, response.getClass());
		
		Assertions.assertEquals(id, response.getId());
		Assertions.assertEquals(codigo, response.getCodigo());
		Assertions.assertEquals(urlEncurtada, response.getUrlEncurtada());
		Assertions.assertEquals(urlOriginal, response.getUrlOriginal());
		
	}
	
	@Test
	void whenFindByCodigoOriginalThenReturnAnEncurtadorInstance() {
		
		Mockito.when(repository.findByCodigo(Mockito.anyString())).thenReturn(encurtador);
		
		Encurtador response = service.findByCodigo(codigo);
		
		Assertions.assertNotNull(response);
		Assertions.assertEquals(Encurtador.class, response.getClass());
		Assertions.assertEquals(id, response.getId());
		Assertions.assertEquals(codigo, response.getCodigo());
		Assertions.assertEquals(urlEncurtada, response.getUrlEncurtada());
		Assertions.assertEquals(urlOriginal, response.getUrlOriginal());
		
	}
	
	@Test
	void whenAddAcessoThenAddOneMoreToTheFieldQtdDeAcessos() {
		
		Encurtador response = new Encurtador(3L, "abC23", "localhost:8080/abC23", "www.youtube.com");
		
		Assertions.assertEquals(response.getQtdDeAcessos(), 0);
		
		
		service.addAcesso(response.getId());
		
		
		
		
	}
	
	@Test
	void whenCriarWithANewUrlThenReturnTheUrlEncurtada() {
		
		Mockito.when(repository.findByUrlOriginal(Mockito.anyString())).thenReturn(null);
		
		var response = service.criar(urlOriginal);
		
		Assertions.assertNotNull(response);
		Assertions.assertEquals(String.class, response.getClass());
	}
	
	@Test
	void whenCriarWithAUrlAlreadyRegisteredThenReturnTheUrlEncurtada() {
		
		Mockito.when(repository.findByUrlOriginal(Mockito.anyString())).thenReturn(encurtador);
		
		var response = service.criar(urlOriginal);
		
		Assertions.assertNotNull(response);
		Assertions.assertEquals(String.class, response.getClass());
		Assertions.assertEquals(urlEncurtada, response);
		
	}
	
	@Test
	void whenGeradorDeCodigoUnicoThenReturnACode() {
		
		Mockito.when(repository.findByCodigo(Mockito.anyString())).thenReturn(null);
		
		var response = service.geradorDeCodigoUnico();
		
		Assertions.assertNotNull(response);
		
	}
	
	
	
	private void startEncurtador() {
		
		encurtador = new Encurtador(id, codigo, urlEncurtada, urlOriginal);
		
	}
	
}
