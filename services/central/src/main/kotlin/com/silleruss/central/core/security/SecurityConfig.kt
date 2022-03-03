package com.silleruss.central.core.security

import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy

@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
            .cors().and()
            .csrf { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests { auth ->
                auth
                    .mvcMatchers(HttpMethod.GET, "/health").permitAll()
                    .mvcMatchers(HttpMethod.GET, "/temp").permitAll()
                    .mvcMatchers(HttpMethod.POST, "/users").hasAnyAuthority("ADMIN")
                    .anyRequest().authenticated()
            }
            .oauth2ResourceServer { it.jwt() }
    }

}