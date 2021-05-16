package br.com.zn43.security_blueprint.data_source;

import br.com.zn43.security_blueprint.custom_user_details.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositoryJPA extends JpaRepository<User, Integer> {

    Optional<User> findByUserName(String userName);

}
