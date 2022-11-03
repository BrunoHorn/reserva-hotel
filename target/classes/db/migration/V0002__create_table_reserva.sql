create table reserva (
	id bigserial primary key,
	hospede_id bigint,
	data_entrada timestamp,
	data_saida timestamp,
	veiculo  boolean,	
	valor_reserva numeric(1000,2),		
    constraint fk_hospede_id foreign key (hospede_id) references hospede (id)
);

