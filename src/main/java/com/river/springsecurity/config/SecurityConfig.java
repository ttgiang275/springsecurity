package com.river.springsecurity.config;

import com.river.springsecurity.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    /*
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

//        Customizer<CsrfConfigurer<HttpSecurity>> customizerCsrf = new Customizer<CsrfConfigurer<HttpSecurity>>() {
//            @Override
//            public void customize(CsrfConfigurer<HttpSecurity> httpSecurityCsrfConfigurer) {
//                httpSecurityCsrfConfigurer.disable();
//            }
//        };

        Customizer<CsrfConfigurer<HttpSecurity>> customizerCsrf = (CsrfConfigurer<HttpSecurity> httpSecurityCsrfConfigurer) -> httpSecurityCsrfConfigurer.disable();
        httpSecurity.csrf(customizerCsrf);


//        Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> customizerAuthorize = new Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry>() {
//            @Override
//            public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry httpSecurityAuthorizationManagerRequestMatcherRegistry) {
//                httpSecurityAuthorizationManagerRequestMatcherRegistry
//                        .requestMatchers("/setup").permitAll()
//                        .anyRequest().authenticated();
//            }
//        };

        Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> customizerAuthorize =
                (AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry httpSecurityAuthorizationManagerRequestMatcherRegistry)
                -> httpSecurityAuthorizationManagerRequestMatcherRegistry.anyRequest().authenticated();
        httpSecurity.authorizeHttpRequests(customizerAuthorize);

        httpSecurity.formLogin(Customizer.withDefaults());

        httpSecurity.httpBasic(Customizer.withDefaults());

        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return httpSecurity.build();

    }
    */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // csrf: for POST, PATCH, DELETE need to pass _csrf token to authenticate
        // authentication for any request
        // popup login
        // disable session

        http
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();

    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
//        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        provider.setPasswordEncoder(this.passwordEncoder());
        return provider;
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("river")
//                .password("12345")
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("12345")
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager();
//
//    }

}
