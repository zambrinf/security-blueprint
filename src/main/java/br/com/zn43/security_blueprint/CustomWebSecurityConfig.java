package br.com.zn43.security_blueprint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class CustomWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder getPassowrdEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DataSource dataSource;


    // AUTHENTICATION MANAGER - the builder can support multiple authentication providers
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // IN MEMORY
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN", "USER")
                .and()
                .withUser("user")
                .password(passwordEncoder.encode("user"))
                .roles("USER");

        // JDBC - H2 automatically configured
        auth.jdbcAuthentication()
                .passwordEncoder(passwordEncoder)
                .dataSource(dataSource)
                // Custom Query when your schema is different - default table names are 'users' and 'authorities'
                .usersByUsernameQuery("SELECT username, password, enabled " +
                        "FROM my_users " +
                        "WHERE username = ?")
                .authoritiesByUsernameQuery("SELECT username, authority " +
                        "FROM my_authorities " +
                        "WHERE username = ?");
    }

    // AUTHORIZATION
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Also a chain opened by authorizeRequests()
        // The least restrictive patterns must be on the bottom
        http.authorizeRequests()
                .antMatchers("/admin").hasAnyRole("ADMIN")
                .antMatchers("/user").hasAnyRole("USER")
                .antMatchers("/").permitAll()
                .and()
                .formLogin();
    }

}
