package com.GraceHopper.GraceHooper.serguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity   														//Anotação utilizada para Habilitar a configuração de WebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {		//Depois de habilitar, extender de outra classe

	@Autowired																//Injeção de dependência 
	private UserDetailsService userDetailsService;

	@Override		//sobrescrever
	protected void configure(AuthenticationManagerBuilder auth) throws Exception { ///throws Exception --> tratar erros
		auth.userDetailsService(userDetailsService);
	}

	@Bean   // anotação Bean:?
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/usuarios/logar").permitAll()
		.antMatchers("/usuarios/cadastrar").permitAll()
		.anyRequest().authenticated()
		.and().httpBasic()
		.and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().cors()
		.and().csrf().disable();
	}
}
