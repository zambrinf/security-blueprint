package br.com.zn43.security_blueprint.security.controller;

import br.com.zn43.security_blueprint.security.models.AuthenticationRequest;
import br.com.zn43.security_blueprint.security.models.AuthenticationResponse;
import br.com.zn43.security_blueprint.security.utils.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authRequest) {
        try {
            return getJwt(authRequest);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("INCORRET USERNAME OR PASSWORD");
        }
    }

    private ResponseEntity<AuthenticationResponse> getJwt(AuthenticationRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername()); // makes it work only with UserDetailsService, doesnt work with in-memory users
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}
