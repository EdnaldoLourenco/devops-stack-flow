package com.williamledo.tds.services;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.williamledo.tds.models.Encurtador;
import com.williamledo.tds.repositories.EncurtadorRepository;

@Service
public class EncurtadorService {

	@Autowired
	private EncurtadorRepository repository;
	
	public List<Encurtador> findAll() {
		return repository.findAll();
	}
	
	public Encurtador findByUrlOriginal(String url) {
		return repository.findByUrlOriginal(url);
	}
	
	public Encurtador findByCodigo(String codigo) {
		return repository.findByCodigo(codigo);
	}
	
	public void addAcesso(Long id) {
		repository.addAcesso(id);
	}
	
	public String criar (String url) {
		
		if (repository.findByUrlOriginal(url) == null) {
			
			Encurtador encurtador = new Encurtador(geradorDeCodigoUnico(), url);
			repository.save(encurtador);
			return encurtador.getUrlEncurtada();
			
		}else {
			Encurtador encurtador = findByUrlOriginal(url);
			return encurtador.getUrlEncurtada();
		}
		
	}
	
	public String geradorDeCodigoUnico() {

        String codigoFinal;
		do {
        	
        	Random random = new Random();
            int randomInt = random.nextInt(Integer.MAX_VALUE);
            String codigo = Integer.toHexString(randomInt);
            codigoFinal = codigo.substring(0, 5);
        	
        } while (repository.findByCodigo(codigoFinal) != null);
        
		return  codigoFinal;
        
	}
	
	public void salvar(Encurtador encurtador) {
		repository.save(encurtador);
	}
	
	
}
