package com.teste.reserva.repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teste.reserva.model.Hospede;
import com.teste.reserva.model.Reserva;

@Repository
public interface ReservaRepository  extends JpaRepository<Reserva, Long>{
	List<Reserva> findByHospedeOrderByDataSaidaAsc(Hospede hospede);
	//List<Reserva> findByHospede(Hospede hospede);
	
}
