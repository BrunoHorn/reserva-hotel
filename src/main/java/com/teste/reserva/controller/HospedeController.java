package com.teste.reserva.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.teste.reserva.dtos.HospedeDto;
import com.teste.reserva.dtos.HospedeInputDto;
import com.teste.reserva.mapper.HospedeMapper;
import com.teste.reserva.model.Hospede;
import com.teste.reserva.service.impl.HospedeServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/hospede-cadastro")
@Api(value="API cadastro de hospede")
public class HospedeController {
	
	@Autowired
    private HospedeServiceImpl hospedeServiceImpl;

	@Autowired
	private HospedeMapper hospedeMapper;
	
	@PostMapping
	@ApiOperation(value="Cadastrar novo hospede")
    public ResponseEntity<HospedeDto> saveCliente(@RequestBody @Valid HospedeInputDto hospedeInputDto){		
        return ResponseEntity.status(HttpStatus.CREATED).body(hospedeServiceImpl.save(hospedeInputDto, null));
    }
    
    @GetMapping
    @ApiOperation(value="Busca lista de hospede cadastrados")
    public ResponseEntity<List<HospedeDto>> getListHospedes(){
        return ResponseEntity.status(HttpStatus.OK).body(hospedeServiceImpl.findAll());
    }
    
    @GetMapping("/{id}")
    @ApiOperation(value="Busca hospede cadastrado pelo ID")
    public ResponseEntity<HospedeDto> getHospedeid(@PathVariable(value = "id") Long id){
    	Hospede hospede =hospedeServiceImpl.findById(id);   	
        return ResponseEntity.status(HttpStatus.OK).body(hospedeMapper.toHospedeDto(hospede));
    }
	   
    @DeleteMapping("/{id}")
    @ApiOperation(value="Deleta hospede cadastrado pelo ID")
    public ResponseEntity<Object> deleteHospede(@PathVariable(value = "id") Long id){
        Hospede hospede = hospedeServiceImpl.findById(id);     
        hospedeServiceImpl.delete(hospede);
        return ResponseEntity.status(HttpStatus.OK).body("Hospede apagado com sucesso");
    }
    
    @PutMapping("/{id}")
    @ApiOperation(value="Atualiza hospede cadastrado pelo ID")
    public ResponseEntity<HospedeDto> updateHospede(@PathVariable(value = "id")Long id,@RequestBody @Valid HospedeInputDto hospedeDto){
        Hospede hospedeupdate = hospedeServiceImpl.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(hospedeServiceImpl.save(hospedeDto, hospedeupdate.getId()));
    }

}
