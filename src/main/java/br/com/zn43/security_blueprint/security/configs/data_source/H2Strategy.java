package br.com.zn43.security_blueprint.security.configs.data_source;

import lombok.NoArgsConstructor;
import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;

@NoArgsConstructor
public class H2Strategy implements DataSourceBuildStrategy {

    @Override
    public DataSource build() {
        DataSourceBuilder<?> builder = DataSourceBuilder.create();
        builder.url("jdbc:postgresql://localhost:5432/mydb?currentSchema=myschema");
        builder.username("myuser");
        builder.password("mypass");
        return builder.build();
    }

}
