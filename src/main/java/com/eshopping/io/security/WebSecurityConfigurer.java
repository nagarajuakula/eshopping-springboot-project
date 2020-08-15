package com.eshopping.io.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.eshopping.io.services.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter{

	@Autowired
	MyUserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder encoder() {
	return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf()
		.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
		.and()
			.authorizeRequests()
            .antMatchers("/h2-console/**", "/eshopping/users/register","/eshopping/users/login", "/eshopping/products").permitAll()
            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .exceptionHandling().accessDeniedPage("/403");
		http.headers().frameOptions().disable();
            
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth
		.userDetailsService(userDetailsService).passwordEncoder(encoder());
		
//			.inMemoryAuthentication()
//			.withUser("user")
//			.password(encoder.encode("password"))
//			.roles("USER")
//			.and()
//			.withUser("admin")
//			.password(encoder.encode("password"))
//			.roles("USER", "ADMIN");
		
	}
	
//	@Bean
//	public CorsFiter corsFilter() {
//		UrlBasedCorsConfigurationSource src = new UrlBasedCorsConfigurationSource();
//		CorsConfiguration config = new CorsConfiguration();
//		config.setAllowCredentials(true);
//		config.addAllowedMethod("OPTIONS");
//		config.addAllowedMethod("GET");
//		config.addAllowedMethod("POST");
//		config.addAllowedMethod("DELETE");
//		config.addAllowedMethod("PUT");
//		config.addAllowedHeader("*");
//		config.addAllowedOrigin("*");
//		src.registerCorsConfiguration("/**",  config);
//		return new CorsFilter(src);
//	}
}
