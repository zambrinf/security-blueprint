package br.com.zn43.security_blueprint;

import br.com.zn43.security_blueprint.security.data_sources.UserRepositoryJPA;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepositoryJPA.class)
public class SecurityBlueprintApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityBlueprintApplication.class, args);
    }

}
