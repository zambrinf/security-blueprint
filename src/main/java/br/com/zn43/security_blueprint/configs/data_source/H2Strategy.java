package br.com.zn43.security_blueprint.configs.data_source;

import lombok.NoArgsConstructor;
import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;

@NoArgsConstructor
public class H2Strategy implements DataSourceBuildStrategy {

    @Override
    public DataSource build() {
        DataSourceBuilder<?> builder = DataSourceBuilder.create();
        builder.driverClassName("org.h2.Driver");
        builder.url("jdbc:h2:mem:testdb");
        builder.username("SA");
        builder.password("");
        return builder.build();
    }

}
