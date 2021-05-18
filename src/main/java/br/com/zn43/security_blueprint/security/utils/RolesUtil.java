package br.com.zn43.security_blueprint.security.utils;

import br.com.zn43.security_blueprint.security.models.Constants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RolesUtil {

    public static Collection<GrantedAuthority> splitRoles(String roles) {
        return Arrays.stream(roles.split(Constants.REGEX_ROLES))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public static String concatAuthorities(Collection<? extends GrantedAuthority> authorities) {
        List<String> authorityStrings = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return String.join(Constants.REGEX_ROLES, authorityStrings);
    }

}
