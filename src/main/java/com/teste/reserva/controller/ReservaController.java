package com.teste.reserva.controller;


import java.util.ArrayList;
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
import com.teste.reserva.dtos.HospedeValoresDto;
import com.teste.reserva.dtos.ReservaDataSaidaDto;
import com.teste.reserva.dtos.ReservaDto;
import com.teste.reserva.mapper.ReservaMapper;
import com.teste.reserva.model.Hospede;
import com.teste.reserva.model.Reserva;
import com.teste.reserva.repository.HospedeRepository;
import com.teste.reserva.repository.ReservaRepository;
import com.teste.reserva.service.impl.ReservaServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/reserva")
@Api(value="API cadastro de Reserva")
public class ReservaController {
	
	@Autowired
	private ReservaRepository reservaRepository;
	
	@Autowired
	private  ReservaServiceImpl reservaServiceImpl;
	
	@Autowired
	private HospedeRepository hospedeRepository;
	
	@Autowired
	private ReservaMapper reservaMapper;
	
	@PostMapping
	@ApiOperation(value="Cadastrar nova Reserva")
    public ResponseEntity<ReservaDto> salvaReserva(@RequestBody @Valid ReservaDto reservaDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaServiceImpl.save(reservaDto,null));
    }
		
    @GetMapping
    @ApiOperation(value="Listar todas as reserva")
    public ResponseEntity<List<ReservaDto>> getListHospedes(){        
    	return ResponseEntity.status(HttpStatus.OK).body(reservaServiceImpl.findAll());
    }
    
    @GetMapping("/hospedes-checkin-abertos")
    @ApiOperation(value="Listar todas as reservas com o checkin em aberto")
    public ResponseEntity<List<HospedeValoresDto>> getListHospedesNoHotel(){
    	List<Hospede> hospedes =hospedeRepository.findAllHospedesEmAtendimento();
    	List<HospedeValoresDto> hospedeValoresDtolist = new ArrayList<>();
    	hospedeValoresDtolist = reservaServiceImpl.retornaListaDeValores(hospedes);

    	return ResponseEntity.status(HttpStatus.OK).body(hospedeValoresDtolist);
    }
    
    @GetMapping("/hospedes-checkout-realizados")
    @ApiOperation(value="Listar todas as reservas com o checkout feito")
    public ResponseEntity<List<HospedeValoresDto>> getListHospedesReservasFinalizadasNoHotel(){
    	List<Hospede> hospedes =hospedeRepository.findAllHospedesAtendimentoFinalizado();
    	List<HospedeValoresDto> hospedeValoresDtolist = new ArrayList<>();
    	hospedeValoresDtolist = reservaServiceImpl.retornaListaDeValores(hospedes);

    	return ResponseEntity.status(HttpStatus.OK).body(hospedeValoresDtolist);
    }
    
   /* public List<HospedeValoresDto> retornaListaDeValores(List<Hospede> hospedes){
    	List<HospedeValoresDto> hospedeValoresDtolist = new ArrayList<>();
    	
        for (Hospede ho: hospedes) {
        	HospedeValoresDto hospedeValoresDto = new HospedeValoresDto();
   		 
   		 	hospedeValoresDto.setNome(ho.getNome());
   		 	hospedeValoresDto.setTelefone(ho.getTelefone());
   		 	hospedeValoresDto.setDocumento(ho.getDocumento());
   		 	List<Reserva> listaReserva =reservaRepository.findByHospedeOrderByDataSaidaAsc(ho);
   		 	for (Reserva reserva:  listaReserva) {   			
   		 		if (hospedeValoresDto.getValorGastoUltimaHospedagem() == null) {
   		 			hospedeValoresDto.setValorGastoUltimaHospedagem(reserva.getValorReserva());
   		 		}
   		 		hospedeValoresDto.setValorTotalGasto(hospedeValoresDto.getValorTotalGasto()+ reserva.getValorReserva());
   		 	}
   		
   		 	hospedeValoresDtolist.add(hospedeValoresDto);
        }
    	
    	return hospedeValoresDtolist;
    	
    }*/

    
    @GetMapping("/{id}")
    @ApiOperation(value="Listar reserva pelo ID")
    public ResponseEntity<ReservaDto> getReservaid(@PathVariable(value = "id") Long id){
    	Reserva reserva = reservaServiceImpl.findById(id);      
        return ResponseEntity.status(HttpStatus.OK).body(reservaMapper.toReservaDto(reserva));
    }
      
    @PutMapping("/{id}")
    @ApiOperation(value="Atualiza reserva cadastrada pelo ID")
    public ResponseEntity<ReservaDto> updateReserva(@PathVariable(value ="id")Long id,@RequestBody @Valid ReservaDto reservaDto){    	
        Reserva reservaupdate = reservaServiceImpl.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(reservaServiceImpl.save(reservaDto ,reservaupdate.getId()));
    }
    
    @DeleteMapping("/{id}")
    @ApiOperation(value="Deleta reserva cadastrada pelo ID")
    public  ResponseEntity <Object> deleteReserva(@PathVariable(value = "id") Long id){
        Reserva reserva = reservaServiceImpl.findById(id);
        reservaServiceImpl.delete(reserva);
        return ResponseEntity.status(HttpStatus.OK).body("Reserva apagado com sucesso");
    }
	
    @PutMapping("/{id}/checkout")
    @ApiOperation(value="Realiza checkout atraves da data de saida")
    public ResponseEntity<ReservaDto> updateDataSaidaReserva(@PathVariable(value = "id")Long id,@RequestBody @Valid ReservaDataSaidaDto reservadataSaidaDto){    	    	
        Reserva reservaupdate = reservaServiceImpl.findById(id);              
        return ResponseEntity.status(HttpStatus.OK).body(reservaServiceImpl.saveDataSaida(reservadataSaidaDto ,reservaupdate.getId()));
    }

    
    
}
