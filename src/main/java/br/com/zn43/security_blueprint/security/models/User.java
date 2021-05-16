package br.com.zn43.security_blueprint.security.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor
@Table(schema = "myschema", name = "tb_user")
public class User implements UserDetails {

    public static final String REGEX_ROLES = ",";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private String password;
    private boolean enabled;
    private String roles;

    public User(int id, String username, String password, boolean enabled, String roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(roles.split(REGEX_ROLES))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public Collection<String> getAuthoritiesStringList() {
        return Arrays.stream(roles.split(REGEX_ROLES))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
