package com.project.library;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/books/add").hasAuthority("LIBRARIAN")
                        .requestMatchers("/books/**").hasAnyAuthority("LIBRARIAN", "READER")
                        .requestMatchers("/login", "/auth/login", "/register", "/error").permitAll()
                        .requestMatchers("/profile").authenticated()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login") // Пользовательская страница логина
                        .loginProcessingUrl("/auth/login") // Обработка логина
                        .defaultSuccessUrl("/profile", true) // Перенаправление после успешного логина
                        .failureUrl("/login?error=true") // В случае ошибки
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                )
                .httpBasic(AbstractHttpConfigurer::disable) // Отключение базовой аутентификации
                .csrf(AbstractHttpConfigurer::disable); // Отключение CSRF (если необходимо)

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
