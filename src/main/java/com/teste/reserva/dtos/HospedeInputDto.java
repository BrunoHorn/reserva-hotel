package com.teste.reserva.dtos;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HospedeInputDto {
    
	//@NotBlank
    @Size(max = 30)
    private String nome;
    
    //@NotBlank
    @Size(max = 11)
    private String documento;
    
    //@NotBlank
    @Size(max = 11)
    private String telefone;
}

