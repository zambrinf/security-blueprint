package br.com.zn43.security_blueprint.security.utils;

import br.com.zn43.security_blueprint.security.models.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.function.Function;

@Component
public class JWTUtil {

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(Constants.JWT_SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        String authorities = RolesUtil.concatAuthorities(userDetails.getAuthorities());
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim(Constants.JWT_AUTHORITIES_NAME, authorities)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Constants.JWT_EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, Constants.JWT_SECRET_KEY)
                .compact();
    }

    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    public Collection<GrantedAuthority> extractAuthorities(String jwt) {
        String authorities = extractClaim(jwt, object -> object.get(Constants.JWT_AUTHORITIES_NAME, String.class));
        return RolesUtil.splitRoles(authorities);
    }

}
