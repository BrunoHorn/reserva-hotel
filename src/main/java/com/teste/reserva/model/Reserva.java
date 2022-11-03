package com.teste.reserva.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
	@JoinColumn(name = "hospede_id")
	private Hospede hospede;

	@Column(name="data_entrada")
	private LocalDateTime dataEntrada ;
	
	@Column(name="data_saida")
	private LocalDateTime dataSaida ;
	
	@Column(name ="veiculo")
	private Boolean adicionalVeiculo;
	
	@Column(name="valor_reserva")
	private Double valorReserva;
	
	public Double getValorReserva() {
		if (valorReserva == null) {
			return 0.0;
		}
		return valorReserva;
	}

}
