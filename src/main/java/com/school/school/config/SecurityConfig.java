package com.school.school.config;


import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/**").authenticated()
                                .requestMatchers("/login").permitAll()
                                .requestMatchers("/public/**").permitAll()
                                .requestMatchers("/","/home").permitAll()
                                .requestMatchers("/holiday/**").permitAll()
                                .requestMatchers("/contact/**").permitAll()
                                .requestMatchers("/saveContact").permitAll()
                                .requestMatchers("/courses/**").permitAll()
                                .requestMatchers("/about/**").permitAll()
                                .requestMatchers("/assets/**").permitAll()
                                .requestMatchers("/dashboard").authenticated()
                                .requestMatchers("/displayProfile").authenticated()
                                .requestMatchers(("/admin/**")).hasRole("ADMIN")
                                .requestMatchers("/student/**").hasRole("STUDENT")
                                .requestMatchers("/displayMessages/**").hasRole("ADMIN")
                                .requestMatchers("/closeMessage/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .csrf(csrf->csrf.ignoringRequestMatchers("/saveContact").ignoringRequestMatchers("/public/**").ignoringRequestMatchers("/api/**"))
                .formLogin(login->login.loginPage("/login").defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll())
                .logout(logout->logout.logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll())
                .httpBasic(Customizer.withDefaults())
//                .headers(headers->headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .build();
    }


    /*@Bean
    public UserDetailsService userDetailsService(){

        UserDetails admin = User.withUsername("admin")
                .password("{noop}admin")
                .roles("ADMIN")
                .build();

        UserDetails user = User
                .withUsername("user")
                .password("{noop}user")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);

    }

*/

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }

}
