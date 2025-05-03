package com.well.banco_digital_CDBW.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.well.banco_digital_CDBW.entity.Endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record EnderecoDto(
		@JsonView({View.Detalhar.class, View.Persistir.class})
		@NotBlank(groups = Complete.class, message = "O campo cep é obrigatorio.")
		@Pattern(regexp = "\\d{5}-\\d{3}", message = "Deve ser um cep no padrão valido.")
		String cep,
		
		@JsonView({View.Detalhar.class, View.Persistir.class})
		@NotBlank(groups = Complete.class, message = "O campo cidade é obrigatorio.")
		String cidade,
		
		@JsonView({View.Detalhar.class, View.Persistir.class})
		@NotBlank(groups = Complete.class, message = "O campo estado é obrigatorio.")
		String estado,
		
		@JsonView({View.Detalhar.class, View.Persistir.class})
		@NotBlank(groups = Complete.class, message = "O campo rua é obrigatorio.")
        String rua, 
        
        @JsonView({View.Detalhar.class, View.Persistir.class})
        @NotNull(groups = Complete.class, message = "O campo número é obrigatorio.")
        Integer numero,
        
		@JsonView(View.Detalhar.class)
        String complemento,
        
        @JsonView({View.Detalhar.class, View.Persistir.class})
        @NotBlank(groups = Complete.class, message = "O campo bairro é obrigatorio.")
        String bairro) {

	public EnderecoDto(Endereco endereco) {
		this(endereco.getCep(), endereco.getCidade(), endereco.getEstado(), endereco.getRua(), endereco.getNumero(), endereco.getComplemento(), endereco.getBairro());
	}

}
