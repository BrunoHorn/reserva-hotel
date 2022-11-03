package com.teste.reserva.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.teste.reserva.dtos.HospedeDto;
import com.teste.reserva.dtos.HospedeInputDto;
import com.teste.reserva.model.Hospede;

@Component
public class HospedeMapper {
	
	public Hospede toHospede(HospedeInputDto hospedeDto) {
		var hospede = new Hospede();
		 BeanUtils.copyProperties(hospedeDto, hospede); 
		 return hospede;
	}
	
	public HospedeDto toHospedeDto(Hospede hospede) {
		var hospedeDto = new HospedeDto();
		 BeanUtils.copyProperties( hospede, hospedeDto); 
		 return hospedeDto;
	}
	
	public List<HospedeDto> toHospedeListDto(List<Hospede> hospede) {
		List<HospedeDto> hospedeListDto = new ArrayList<>();		
		for (Hospede hp : hospede) {
			var hospedeDto = new HospedeDto();
			 hospedeDto.setId(hp.getId());
			 hospedeDto.setNome(hp.getNome());
	   		 hospedeDto.setTelefone(hp.getTelefone());
	   		 hospedeDto.setDocumento(hp.getDocumento());
			hospedeListDto.add(hospedeDto);
		}				 
		 return hospedeListDto;
	}

}
