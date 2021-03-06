# locadora-filmes

Este projeto é um serviço REST de uma locadora de filmes. O sistema permiti a criação de usuários (clientes), logon e logoff (autenticação básica) de um usuário nas operações principais. Possui listagem de filmes disponíveis, locação de um filme, devolução de um filme, e pesquisa de filme pelo título.

Como acessar o banco da aplicação:

- O projeto já vem com um banco acoplado em memória o H2 e com dados pre populados. Mas caso queira usar outro banco como o oracle por exemplo, então segue os scripts para criação de tabela e massa de dados em \src\main\resources\data.sql
- Baixar o projeto e executá-lo.
- com o projeto rodando é habilitado um console do banco, em http://localhost:8080/console
- informe em JDBC URL: jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;Mode=Oracle
- informe User Name: sa
- informe Driver Class: org.h2.Driver
- o Password: pode ficar em branco e após clicar em connect

Como acessar a aplicação:
- com o projeto em execução é só acessar o endereço do swagger da api em: http://localhost:8080/swagger-ui.html#
