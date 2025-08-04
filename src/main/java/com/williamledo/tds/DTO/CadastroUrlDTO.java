package com.williamledo.tds.DTO;

import jakarta.validation.constraints.NotBlank;

public record CadastroUrlDTO (
		
		@NotBlank
		String url
		
		) {

}
