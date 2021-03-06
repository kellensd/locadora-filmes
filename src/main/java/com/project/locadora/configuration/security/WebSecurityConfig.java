package com.project.locadora.configuration.security;

import com.project.locadora.configuration.security.CustomAuthenticationProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private CustomAuthenticationProvider customAuthenticationProvider;

    public WebSecurityConfig(CustomAuthenticationProvider customAuthenticationProvider) {
        this.customAuthenticationProvider = customAuthenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity
                    .authorizeRequests().antMatchers("/").permitAll()
                .and()
                    .authorizeRequests().antMatchers("/console/**").permitAll()
                .and()
                    .authorizeRequests()
                        .antMatchers(HttpMethod.GET, "/usuarios").authenticated()
                        .antMatchers(HttpMethod.DELETE, "/usuarios").authenticated()
                .and()
                    .authorizeRequests()
                        .antMatchers(HttpMethod.POST, "/filmes").authenticated()
                        .antMatchers(HttpMethod.DELETE, "/filmes").authenticated()
                        .antMatchers(HttpMethod.PATCH, "/filmes").authenticated()
                .and()
                    .httpBasic();
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
    }

    protected void configure(AuthenticationManagerBuilder authBuilder) {
            authBuilder.authenticationProvider(customAuthenticationProvider);
    }
}
