package br.com.zn43.security_blueprint.security.configs.data_source;

import lombok.NoArgsConstructor;
import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;

@NoArgsConstructor
public class PostgresStrategy implements DataSourceBuildStrategy {

    @Override
    public DataSource build() {
        DataSourceBuilder<?> builder = DataSourceBuilder.create();
        builder.url("jdbc:h2:mem:testdb");
        builder.username("SA");
        builder.password("");
        return builder.build();
    }

}
