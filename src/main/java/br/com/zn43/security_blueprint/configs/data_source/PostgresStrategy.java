package br.com.zn43.security_blueprint.configs.data_source;

import lombok.NoArgsConstructor;
import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;

@NoArgsConstructor
public class PostgresStrategy implements DataSourceBuildStrategy {

    @Override
    public DataSource build() {
        DataSourceBuilder<?> builder = DataSourceBuilder.create();
        builder.driverClassName("org.postgresql.Driver");
        builder.url("jdbc:postgresql://localhost:5432/mydb?currentSchema=myschema");
        builder.username("myuser");
        builder.password("mypass");
        return builder.build();
    }

}
