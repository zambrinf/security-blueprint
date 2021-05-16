package br.com.zn43.security_blueprint.configs;

import br.com.zn43.security_blueprint.jwt_authentication.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@EnableWebSecurity
public class CustomWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder getPassowrdEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    // AUTHENTICATION MANAGER - the builder can support multiple authentication providers
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // IN MEMORY
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password(passwordEncoder.encode("admin"))
//                .roles("ADMIN", "USER")
//                .and()
//                .withUser("user")
//                .password(passwordEncoder.encode("user"))
//                .roles("USER");

        // JPA - need to create the UserDetailsService that retrieves the UserDetails - not about JPA itself
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    // AUTHORIZATION
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Also a chain opened by authorizeRequests()
        // The least restrictive patterns must be on the bottom
        http
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin").hasAnyRole("ADMIN")
                .antMatchers("/user").hasAnyRole("USER")
                .antMatchers("/authenticate").permitAll()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}
