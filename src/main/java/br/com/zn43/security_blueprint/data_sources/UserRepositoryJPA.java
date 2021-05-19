package br.com.zn43.security_blueprint.data_sources;

import br.com.zn43.security_blueprint.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositoryJPA extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

}
