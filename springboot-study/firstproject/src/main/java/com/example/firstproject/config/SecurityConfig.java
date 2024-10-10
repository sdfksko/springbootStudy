package com.example.firstproject.config;

import com.example.firstproject.config.handler.CustomAuthenticationFailureHandler;

import com.example.firstproject.config.oauth.SpringSecOAuth2PrincipalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration      // Spring Bean에서 사용할 수 있도록 세팅
@EnableWebSecurity  // Spring Security를 활성화하도록 세팅
@EnableMethodSecurity(securedEnabled = true)    // @Secured, @PreAuthorize, @PostAuthorize 어노테이션 사용해주는 어노테이션
public class SecurityConfig {

    @Autowired
    SpringSecOAuth2PrincipalDetailsService springSecOAuth2PrincipalDetailsService;

    @Bean
    BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
//          .csrf((csrf) -> csrf.disable())     // 위의 코드와 같음
                .authorizeHttpRequests((authorizeHttpRequests)
                        -> authorizeHttpRequests.requestMatchers("/user/**", "/articles/**").authenticated()
                                                .requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
                                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                                .anyRequest().permitAll()
                ).formLogin(formLogin ->
                        formLogin.loginPage("/loginForm")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/articles")
//                                .failureUrl("/login-failure")
                                .failureHandler(customAuthenticationFailureHandler())
                //  invalidateHttpSession는 로그아웃시 Session을 초기화
                ).logout(logout ->
                        logout.logoutSuccessUrl("/loginForm")
                                .invalidateHttpSession(true)
                ).oauth2Login(oauth2Login ->
                        oauth2Login.loginPage("/loginForm")
                                .userInfoEndpoint(userInfoEndpoint ->
                                        userInfoEndpoint.userService(springSecOAuth2PrincipalDetailsService))
                )
        ;
        return http.build();
    }

    @Bean
    public AuthenticationFailureHandler customAuthenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }
}
