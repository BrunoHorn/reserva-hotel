package com.teste.reserva.mapper;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import com.teste.reserva.dtos.HospedeInputDto;
import com.teste.reserva.dtos.ReservaDto;
import com.teste.reserva.model.Reserva;

@Component
public class ReservaMapper {
	
	public Reserva toReserva(ReservaDto reservaDto) {
		var reserva = new Reserva();
		 BeanUtils.copyProperties(reservaDto, reserva); 
		 return reserva;
	}
	
	public ReservaDto toReservaDto(Reserva reserva) {
		var reservaDto = new ReservaDto();
		HospedeInputDto hospededto = new HospedeInputDto();
		BeanUtils.copyProperties( reserva, reservaDto);
		hospededto.setNome(reserva.getHospede().getNome());
		hospededto.setDocumento(reserva.getHospede().getDocumento());
		hospededto.setTelefone(reserva.getHospede().getTelefone());
		reservaDto.setHospede(hospededto);
		return reservaDto;
	}
	
	public List<ReservaDto> toReservaListDto(List<Reserva> reserva) {
		List<ReservaDto> reservaListDto = new ArrayList<>();		
		for (Reserva rs : reserva) {
			HospedeInputDto hospedeDto = new HospedeInputDto();
			ReservaDto reservaDto = new ReservaDto();			
			reservaDto.setIdReserva(rs.getId());
			hospedeDto.setNome(rs.getHospede().getNome());
			hospedeDto.setDocumento(rs.getHospede().getDocumento());
			hospedeDto.setTelefone(rs.getHospede().getTelefone());			
			reservaDto.setHospede(hospedeDto);
			reservaDto.setDataEntrada(rs.getDataEntrada());
			reservaDto.setDataSaida(rs.getDataSaida());
			reservaDto.setAdicionalVeiculo(rs.getAdicionalVeiculo());
			
			reservaListDto.add(reservaDto);
			
		}				 
		 return reservaListDto;
	}

	

}
