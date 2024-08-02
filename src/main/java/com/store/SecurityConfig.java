package com.store;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        // Custom queries for authentication and authority retrieval
        manager.setUsersByUsernameQuery(
            "SELECT username, password, 1 as enabled FROM Accounts WHERE username = ?"
        );
        manager.setAuthoritiesByUsernameQuery(
            "SELECT a.Username as username, r.id as authority " +
            "FROM Authorities a " +
            "JOIN Roles r ON a.Roleid = r.id " +
            "WHERE a.Username = ?"
        );
        return manager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .cors(cors -> cors.disable())
            .authorizeHttpRequests(auth -> {
                auth.requestMatchers("/order/**").authenticated();
                auth.requestMatchers("/admin/**").hasAnyRole("STAF", "DIRE");
                auth.requestMatchers("/rest/authorities").hasRole("DIRE");
                auth.anyRequest().permitAll();
            })
            .exceptionHandling(denied -> denied.accessDeniedPage("/security/unauthoried"))
            .formLogin(login -> {
                login.loginPage("/security/login/form")
                     .loginProcessingUrl("/security/login")
                     .defaultSuccessUrl("/security/login/success", false)
                     .failureUrl("/security/login/error")
                     .permitAll();
            })
            .rememberMe(remember -> remember.tokenValiditySeconds(86400))
            .logout(logout -> {
                logout.logoutUrl("/security/logoff")
                      .logoutSuccessUrl("/security/logoff/success");
            });

        return http.build();
    }
}
