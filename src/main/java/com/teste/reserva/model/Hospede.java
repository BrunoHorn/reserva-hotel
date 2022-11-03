package com.teste.reserva.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hospede{
	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
   
    private String nome;
   

    private String documento;
    

    private String telefone;
    
   //@OneToMany(mappedBy = "hospede",orphanRemoval = true, cascade = CascadeType.ALL)
   // private List <Reserva> reservas = new ArrayList<>();
    //Lista de reserva aqui
    //List<REserva> reservas

}
