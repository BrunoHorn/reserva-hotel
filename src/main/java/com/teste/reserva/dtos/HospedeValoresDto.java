package com.teste.reserva.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HospedeValoresDto {
	
	private String nome;
	private String telefone;
	private String documento;
	
	private Double valorTotalGasto;
	private Double valorGastoUltimaHospedagem;
	
	public Double getValorTotalGasto() {
		if (valorTotalGasto== null) {
			return 0.0;
		}
		return valorTotalGasto;
	}

}
