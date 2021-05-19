Spring Security Blueprint: Ready to use with Username and Password authentication which generates a JWT to authorization proccess
- Choose the database in application.properties file, commenting the one you don't want to
- If Postgres is your choice, run `docker-compose up -d` in root directory
- To add another DataSource just create a new class that implements DataSourceBuildStrategy and add that to DataSourceStrategyFactory
- Import Postman Collection, available in root directory, using `Auth` request send a POST request to `/authenticate`, copy the JWT to the Bearer Token  in `Admin Test` request and send it
