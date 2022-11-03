package com.teste.reserva.dtos;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HospedeDto {
    
	private Long id;
	
    private String nome;

    private String documento;

    private String telefone;
}
