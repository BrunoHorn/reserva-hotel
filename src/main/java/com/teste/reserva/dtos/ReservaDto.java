package com.teste.reserva.dtos;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservaDto {
	
	private Long idReserva;
	
	@NotNull
	private HospedeInputDto hospede ;
	
	@NotNull
	private LocalDateTime dataEntrada ;
	
	
	private LocalDateTime dataSaida ;
	
	private Boolean adicionalVeiculo;
	

}
