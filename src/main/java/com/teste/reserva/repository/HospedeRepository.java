package com.teste.reserva.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.teste.reserva.model.Hospede;

@Repository
public interface HospedeRepository extends JpaRepository <Hospede, Long> {
	
	boolean existsByDocumento(String documento);
	boolean existsByTelefone(String telefone);
	
	Optional<Hospede> findBynome(String nome);
	Optional<Hospede> findByDocumento(String documento);
	Optional<Hospede> findByTelefone(String telefone);
	
	@Query(value = "select * from hospede h "
			+ " inner join reserva r on (h.id = r.hospede_id) "
			+ " where r.data_saida is  null ", nativeQuery = true)
	List<Hospede> findAllHospedesEmAtendimento();
	
	@Query(value = "select * from hospede h "
			+ " inner join reserva r on (h.id = r.hospede_id) "
			+ " where r.data_saida is not null ", nativeQuery = true)
	List<Hospede> findAllHospedesAtendimentoFinalizado();

}
