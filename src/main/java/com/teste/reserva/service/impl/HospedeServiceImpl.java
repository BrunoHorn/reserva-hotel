package com.teste.reserva.service.impl;

import java.util.List;
import java.util.Objects;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.teste.reserva.dtos.HospedeDto;
import com.teste.reserva.dtos.HospedeInputDto;
import com.teste.reserva.mapper.HospedeMapper;
import com.teste.reserva.model.Hospede;
import com.teste.reserva.repository.HospedeRepository;

@Service
public class HospedeServiceImpl {
	
	@Autowired
	private HospedeRepository hospedeRepository;
	
	@Autowired
	private HospedeMapper hospedeMapper;
	

    @Transactional
	public HospedeDto save(HospedeInputDto hospedeDto, Long id) {      	   	
    	var hospede = hospedeMapper.toHospede(hospedeDto);
        
    	if(existsByDocumento(hospede.getDocumento())) {
    		throw new RuntimeException("Já possui um hospede cadastrado com este documento");
    	}
    	
        if (Objects.nonNull(id)) {
        	hospede.setId(id);
        }
    	
		var hospedeSalvo =  hospedeRepository.save(hospede);
		
		return hospedeMapper.toHospedeDto(hospedeSalvo);
	}
	
    public boolean existsByDocumento(String documento) {
        return hospedeRepository.existsByDocumento(documento);
    }
    
    public boolean existsByTelefone(String Telefone) {
    	return hospedeRepository.existsByTelefone(Telefone);
    }
    
    public List<HospedeDto> findAll(){
    	
    	return hospedeMapper.toHospedeListDto(hospedeRepository.findAll());
    }
    
    public Hospede findById (Long id){
    	var hospedeOptional = hospedeRepository.findById(id);
		  if (hospedeOptional.isEmpty()) {           
	      		throw new RuntimeException("Hospede não encontrada pelo Id");
	      }
    	return hospedeOptional.get();
    }
    
    @Transactional
	public void delete(Hospede hospede) {
		hospedeRepository.delete(hospede);
		
	}
 
	

}
