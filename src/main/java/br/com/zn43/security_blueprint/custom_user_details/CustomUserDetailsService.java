package br.com.zn43.security_blueprint.custom_user_details;

import br.com.zn43.security_blueprint.data_sources.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepositoryJPA userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("USERNAME NOT FOUND"));
        return new CustomUserDetails(user);
    }

}
