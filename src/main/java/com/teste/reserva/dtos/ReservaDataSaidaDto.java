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
public class ReservaDataSaidaDto {
	
	@NotNull
	private LocalDateTime dataSaida ;

}
