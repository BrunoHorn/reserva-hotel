package com.teste.reserva.service.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.teste.reserva.dtos.HospedeInputDto;
import com.teste.reserva.dtos.HospedeValoresDto;
import com.teste.reserva.dtos.ReservaDataSaidaDto;
import com.teste.reserva.dtos.ReservaDto;
import com.teste.reserva.mapper.ReservaMapper;
import com.teste.reserva.model.Hospede;
import com.teste.reserva.model.Reserva;
import com.teste.reserva.repository.HospedeRepository;
import com.teste.reserva.repository.ReservaRepository;

@Service
public class ReservaServiceImpl {
	
	@Autowired
	private ReservaRepository reservaRepository;
		
	@Autowired
	private ReservaMapper reservaMapper;
	
	@Autowired
	private  HospedeRepository hospedeRepository ;
		
	@Transactional
	public ReservaDto save(ReservaDto reservaDto,Long id) {
		
		var reserva = new Reserva();				
		var hospede = procurarHospedePorCampoInformadoNaReserva(reservaDto.getHospede());
		reserva.setHospede(hospede);
		
		BeanUtils.copyProperties(reservaDto, reserva);
		
		//reserva.setDataEntrada((LocalDateTime.now(ZoneId.of("UTC"))));
		
        if (Objects.nonNull(id)) {
        	reserva.setId(id);
        }
		
		if (Objects.nonNull(reserva.getDataSaida())) {
			reserva.setValorReserva(concluirReserva(reserva));		
		}
		reservaRepository.save(reserva);
		
		return reservaMapper.toReservaDto(reserva);
	} 
	
	
	private Hospede procurarHospedePorCampoInformadoNaReserva(HospedeInputDto hospedeInputDto) {		
		var hospedeOptional = hospedeRepository.findBynome(hospedeInputDto.getNome());		
		if (hospedeOptional.isEmpty() ) {
			hospedeOptional = hospedeRepository.findByDocumento(hospedeInputDto.getDocumento());
			}
		if (hospedeOptional.isEmpty() ) {
				hospedeOptional = hospedeRepository.findByTelefone(hospedeInputDto.getTelefone());
			}		
		Hospede hospede = hospedeOptional.get();
		return hospede;
	}
	
	private Double concluirReserva(Reserva reserva) {
		LocalDate dataEntradaControle=reserva.getDataEntrada().toLocalDate();
		
		Double valorTotalReserva = 0.0;
		 while(!dataEntradaControle.equals(reserva.getDataSaida().toLocalDate())){			
			var diaDaSemana = reserva.getDataEntrada().getDayOfWeek();
			
			if (DayOfWeek.SATURDAY == diaDaSemana ||  
					DayOfWeek.SUNDAY == diaDaSemana) {
				
				valorTotalReserva += 150.0;
				if (reserva.getAdicionalVeiculo()) {
					valorTotalReserva =adicionalGaragem(valorTotalReserva,20.0);
				}
			} else {
				valorTotalReserva += 130.0;
				if (reserva.getAdicionalVeiculo()) {
					valorTotalReserva =adicionalGaragem(valorTotalReserva,15.0);
				}
			}
			dataEntradaControle =dataEntradaControle.plusDays(1);
		 }
		  
		 if (reserva.getDataSaida().getHour() >= 16 ) {
				if(  reserva.getDataSaida().getHour() == 16 &&reserva.getDataSaida().getMinute() > 30) {
					valorTotalReserva += adicionalDeDiaria(reserva.getDataSaida());
				} else {
					valorTotalReserva += adicionalDeDiaria(reserva.getDataSaida());
					}
				}
		return valorTotalReserva;
	}
	
	private Double adicionalDeDiaria(LocalDateTime diaSaida) {
		Double diariaExtra;
		if (DayOfWeek.SATURDAY == diaSaida.getDayOfWeek() ||  
				DayOfWeek.SUNDAY == diaSaida.getDayOfWeek()) {
			diariaExtra = 150.0;	
		} else {
			diariaExtra =130.0;
		}
		
		return diariaExtra;
	}

	public List <ReservaDto> findAll() {		
		return reservaMapper.toReservaListDto(reservaRepository.findAll());				
	}

	public Reserva findById(Long id) {		
		var reservaOptional =  reservaRepository.findById(id);
		
		  if (reservaOptional.isEmpty()) {           
      		throw new RuntimeException("Reserva não encontrada pelo Id");
      }

		  return reservaOptional.get();
	}


	public Double adicionalGaragem (double valorTotalReserva,double valorVaga) {
		
		return valorTotalReserva + valorVaga;
	}
	
	public void delete(Reserva reserva) {
		reservaRepository.delete(reserva);
		
	}


	public ReservaDto saveDataSaida(@Valid ReservaDataSaidaDto reservadataSaidaDto, Long id) {
		var reserva = new Reserva();
		
		if (Objects.nonNull(id)) {
			reserva= reservaRepository.findById(id).get();			
			reserva.setDataSaida(reservadataSaidaDto.getDataSaida());
        }		
		reserva.setValorReserva(concluirReserva(reserva));
		reservaRepository.save(reserva);				
		return reservaMapper.toReservaDto(reserva);
	}

    public List<HospedeValoresDto> retornaListaDeValores(List<Hospede> hospedes){
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
    	
    }

}
