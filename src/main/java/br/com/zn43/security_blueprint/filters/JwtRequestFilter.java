package br.com.zn43.security_blueprint.filters;

import br.com.zn43.security_blueprint.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private static final String BEARER_PREFIX = "Bearer ";

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (nonNull(authorizationHeader) && Boolean.TRUE.equals(authorizationHeader.startsWith(BEARER_PREFIX))) {
            jwt = authorizationHeader.substring(BEARER_PREFIX.length());
            username = jwtUtil.extractUsername(jwt);
        }

        if (nonNull(username) && isNull((SecurityContextHolder.getContext().getAuthentication())) && Boolean.TRUE.equals(jwtUtil.validateToken(jwt))) { // Authentication null means the request user is not logged in a stateful session
            Collection<? extends GrantedAuthority> authorities = jwtUtil.extractAuthorities(jwt);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(usernamePasswordAuthenticationToken);
            SecurityContextHolder.setContext(context);
        }

        filterChain.doFilter(request, response);
    }

}
