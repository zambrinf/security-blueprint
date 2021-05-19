package br.com.zn43.security_blueprint.configs.data_source;

public enum DataSourceStrategyFactory {

    H2(new H2Strategy()),
    POSTGRES(new PostgresStrategy());

    private DataSourceStrategyFactory(DataSourceBuildStrategy strategy) {
        this.strategy = strategy;
    }

    private DataSourceBuildStrategy strategy;

    public DataSourceBuildStrategy getStrategy() {
        return strategy;
    }

}
