#Spring Security Blueprint

<p align="center">
    <img src="https://dzone.com/storage/temp/12434118-spring-boot-logo.png" height="200"/> 
</p>

Ready to use with Username and Password authentication which generates a JWT to authorization proccess
### How to use
- Choose the database in application.properties file, commenting the one you don't want to
- If Postgres is your choice, run `docker-compose up -d` in root directory
- To add another DataSource just create a new class that implements DataSourceBuildStrategy and add that to DataSourceStrategyFactory
- Import Postman Collection, available in root directory, using `Auth` request send a POST request to `/authenticate`, copy the JWT to the Bearer Token  in `Admin Test` request and send it
### Requirements
- JDK 11
- Docker and docker-compose (if running Postgres)