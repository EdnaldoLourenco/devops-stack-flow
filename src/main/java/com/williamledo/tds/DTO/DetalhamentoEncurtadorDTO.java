package com.williamledo.tds.DTO;

import com.williamledo.tds.models.Encurtador;

public record DetalhamentoEncurtadorDTO(String urlEncurtada) {

	public DetalhamentoEncurtadorDTO(Encurtador encurtador) {
		this(encurtador.getUrlEncurtada());
	}
	
}
