# Spring Security Blueprint <hr>

<p align="center">
    <img src="https://dzone.com/storage/temp/12434118-spring-boot-logo.png" height="200"/> 
</p>

Ready to use with Username and Password authentication which generates a JWT to authorization proccess
### How to use <hr>
- Choose the database in application.properties file, commenting the one you don't want to
- If Postgres is your choice, run `docker-compose up -d` in root directory
- To add another DataSource just create a new class that implements DataSourceBuildStrategy and add that to DataSourceStrategyFactory
- Import Postman Collection, available in root directory, using `Auth` request send a POST request to `/authenticate`, copy the JWT to the Bearer Token  in `Admin Test` request and send it
### Requirements <hr>
- JDK 11
- Docker and docker-compose (if running Postgres)

### References <hr>
- https://github.com/koushikkothagal/spring-security-jwt 
<hr>

# Modelo Spring Security <hr>
Modelo pronto para usar com autenticação por usuário e senha que gera um JWT para o processo de autorização
### Como usar <hr>
- Escolha um banco de dados no arquivo application.properties, comentando o que não irá usar
- Se for utilizar Postgres, rodar o comando `docker-compose up -d` no diretório base
- Para adicionar outro banco de dados é nececssário criar uma nova classe que implementa DataSourceBuildStrategy e adicioná-la na DataSourceStrategyFactory
- Importar a coleção do Postman, que está disponível na pasta raiz, utilizar a request `Auth` mandando uma requisição POST para o endpoint `/authenticate`, copiar o JWT para a request `Admin Test` e enviá-la
### Requisitos <hr>
- JDK 11
- Docker e docker-compose (se for rodar Postgres)

### Referências <hr>
- https://github.com/koushikkothagal/spring-security-jwt