package br.com.zn43.security_blueprint.configs.data_source;

import javax.sql.DataSource;

public interface DataSourceBuildStrategy {

    DataSource build();

}
