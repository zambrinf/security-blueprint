package br.com.zn43.security_blueprint.jwt_authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {

    private String jwt;

}
