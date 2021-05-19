Spring Security Blueprint
- Choose the database in application.properties file, commenting the one you don't want to
- If Postgres is your choice, run `docker-compose up -d` in root directory
- Authenticate using POSTMAN, send a POST request to `/authenticate` using the AuthenticationRequest object
- To add another DataSource just create a new class that implements DataSourceBuildStrategy and add that to DataSourceStrategyFactory