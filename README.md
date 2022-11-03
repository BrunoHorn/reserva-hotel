Projeto Reserva Hotel 

💻 Sobre o projeto

O O projeto consiste em um  cadastro de hóspedes,  e um
cadastro de check in e check out.

⚙️ Funcionalidades

✳️Hospedes :
  * cadastro de  novo hospedes;
  * consultas de hospedes;
  * Alterações de dados dos hospedes;
  * Exclusão de hospdes; 
   
✳️Reservas : 
  * Realizar reserva atraves do check in;                        
  * Finalizar reserva enviando a data de check out;                           
  * Consulta de revervas por hospedes que realizaran check in;                         
  * Consulta de revervas por hospedes que realizaran check out;                         
  * exclusão de reservas

🛠 Técnologias e padrão utilizadas
   * Arquitetura padrão MVC;
   * Spring Data;
   * Java 11;
   * Maven;
   * Docker;
   * Flyway;
   * Postgres;
   * Lombok;
   * Swagger;
  
🧭 Rodando a aplicação
    Clonar o repositório do projeto :
      git clone https://github.com/BrunoHorn/reserva-hotel.git
    
  O projeto baixará as dependências necessárias e buildará com sucesso. 
  Caso não complete com sucesso, verifique o log do build para encontrar possíveis erros.

🐋 Configurar o DB Postgres localmente com o Docker
    Com o terminal dentro do diretório do projeto, executar o docker-compose abaixo :
      docker-compose up
            
Conectar-se ao DB com o manager de sua preferência. O usuário e senha do DB podem ser consultados no arquivo application.yml

A documentação da API é feita através do swagger, e quando a aplicação estiver rodando em ambiente local você pode acessá-la pelo :
http://localhost:8080/swagger-ui.html
    
💡 Regras de negócio implementadas :

   ●	Consultar hóspedes que já realizaram o check in e  ainda estão no hotel.
   
   ![image](https://user-images.githubusercontent.com/66273298/199784644-70253446-8eae-4254-bf58-117483195703.png)

   ●	Consultar hóspedes que já realizaram o check in e não estão mais no hotel.
   
   ![image](https://user-images.githubusercontent.com/66273298/199784113-3170d546-681f-41c4-9227-92fa8c2045b0.png)

  
  
  
  
  
