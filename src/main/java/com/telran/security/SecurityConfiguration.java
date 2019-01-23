package com.telran.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public SecurityFilter securityFilter() {
        return new SecurityFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
                .antMatchers("/non-secured").permitAll()
                .antMatchers("/secured").authenticated();

        http.addFilterBefore(securityFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
