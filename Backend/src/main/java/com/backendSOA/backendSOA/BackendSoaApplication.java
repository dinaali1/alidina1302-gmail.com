package com.backendSOA.backendSOA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BackendSoaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendSoaApplication.class, args);
	}


	@Configuration
	public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

		@Override
		public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
			.antMatchers("/api/v1/users").permitAll()
			.antMatchers("/api/v1/agents").permitAll()
			.antMatchers("/api/v1/professors").permitAll()
			.antMatchers("/api/v1/authentication").permitAll()
			.antMatchers("/api/v1/subejcts").permitAll()
			.antMatchers("/api/v1/sessions").permitAll()
			.antMatchers("/api/v1/presence").permitAll()
			.antMatchers("/api/v1/statistics").permitAll()
			.antMatchers("/api/v1/students").permitAll()
			.antMatchers("/api/v1/promotions/").permitAll();
		}

		@Bean
		public WebMvcConfigurer corsConfigurer() {
			return new WebMvcConfigurer() {
				@Override
				public void addCorsMappings(CorsRegistry registry) {
					registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE")
					.allowedOrigins("*")
					.allowedHeaders("*");
				}
			};
		}
	}

}
