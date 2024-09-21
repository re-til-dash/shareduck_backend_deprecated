package com.shareduck.shareduck.common.config;

import java.util.Arrays;

import com.shareduck.shareduck.common.security.handler.AuthenticationEntryPointHandler;
import com.shareduck.shareduck.common.security.handler.LogoutSuccessCustomHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationEntryPointHandler authenticationEntryPointHandler;
    private final LogoutSuccessCustomHandler logoutSuccessCustomHandler;
    private final JwtFilterDsl jwtFilterDsl;

    @Bean
    public WebSecurityCustomizer configureH2ConsoleEnable() {
        return web -> web.ignoring()
            .requestMatchers(PathRequest.toH2Console());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.with(jwtFilterDsl, JwtFilterDsl::build);
        http.headers(headerConfig -> headerConfig.frameOptions(FrameOptionsConfig::disable));
        http.authorizeHttpRequests(authorize ->
                authorize
                    .requestMatchers("/swagger-ui/**", "/api-docs/**").permitAll()
                    .requestMatchers("/error/**").permitAll()
                    .requestMatchers("/api/auth/reissue").permitAll()
                    .requestMatchers(HttpMethod.POST,"/api/v1/users").permitAll()
                    .anyRequest().authenticated())
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(apiConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(
                        exception -> exception.authenticationEntryPoint(authenticationEntryPointHandler))
                .logout(
                        logout ->
                                logout.logoutSuccessHandler(logoutSuccessCustomHandler).logoutUrl("/api/logout"));
        http.oauth2Login(Customizer.withDefaults());

        return http.build();
    }

    private CorsConfigurationSource apiConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedMethods(
                Arrays.asList("GET", "POST", "PATCH", "DELETE", "OPTIONAL", "OPTION"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setExposedHeaders(
                Arrays.asList("Authorization", "Location", "Refresh", "authorization"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}