package org.example.smart.security.config;

import java.util.List;

import org.example.smart.security.filter.JwtAuthenticationFilter;
import org.example.smart.security.util.JwtUtil;
import org.example.smart.service.AuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final JwtUtil jwtUtil;
	private final AuthService authService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
			.formLogin(AbstractHttpConfigurer::disable)
			.httpBasic(AbstractHttpConfigurer::disable)
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests(auth -> auth.
				requestMatchers("/api/v1/auth/sign-up", "/api/v1/auth/sign-in")
				.permitAll()
				.requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui/index.html")
				.permitAll()
				.anyRequest()
				.authenticated())
			// .exceptionHandling(exception -> exception
			// 	.authenticationEntryPoint((request, response, authException) -> {
			// 		response.setStatus(ErrorCode.UNAUTHORIZED.getCode());
			// 		response.getWriter().write(ErrorCode.UNAUTHORIZED.getMessage());
			// 	})
			// )
			.cors(cors -> cors.configurationSource(corsConfigurationSource()))
			.addFilterBefore(new JwtAuthenticationFilter(jwtUtil, authService),
				UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfig = new CorsConfiguration();

		// corsConfig.setAllowedOrigins(List.of("*"));
		corsConfig.setAllowedOriginPatterns(List.of("*"));
		corsConfig.setAllowedMethods(List.of("GET", "POST", "PATCH", "DELETE"));
		corsConfig.setAllowedHeaders(List.of("*"));
		corsConfig.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);
		return source;
	}
}
