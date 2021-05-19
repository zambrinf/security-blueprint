package br.com.zn43.security_blueprint.security.configs.data_source;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class DataSourceConfig {

    @Value("${spring.datasource.platform}")
    private String databasePlatform;

    @Bean
    public DataSource getDataSource() {
        return DataSourceStrategyFactory.valueOf(databasePlatform.toUpperCase())
                .getStrategy()
                .build();
    }

}
