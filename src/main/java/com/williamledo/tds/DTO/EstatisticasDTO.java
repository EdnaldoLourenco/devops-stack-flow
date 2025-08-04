package com.williamledo.tds.DTO;

import com.williamledo.tds.models.Encurtador;

public record EstatisticasDTO(String urlEncurtada, Integer qtdDeAcesso, String urlOriginal) {

	public EstatisticasDTO(Encurtador encurtador) {
		this(encurtador.getUrlEncurtada(), encurtador.getQtdDeAcessos(), encurtador.getUrlOriginal());
	}
	
}
