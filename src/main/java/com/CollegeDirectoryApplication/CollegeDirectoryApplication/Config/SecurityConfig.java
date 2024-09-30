package com.CollegeDirectoryApplication.CollegeDirectoryApplication.Config;

import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.Role;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.JwtFilter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(request->{
                // role base access
                request.requestMatchers(
                        "/auth/**" ,
                        "/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/swagger-ui/index.html",
                        "/v3/api-docs","/v2/api-docs", "/swagger-ui/**",
                        "/webjars/**" ).permitAll(); //this api access any user even which have not registered
                request.requestMatchers("/student/**").hasRole(Role.STUDENT.toString());
                request.requestMatchers("/admin/students").hasAnyRole(Role.FACULTY.toString() , Role.ADMINISTRATOR.toString());
                request.requestMatchers("/admin/**").hasRole(Role.ADMINISTRATOR.toString());
                request.requestMatchers("/faculty/**").hasRole(Role.FACULTY.toString());
                request.requestMatchers(HttpMethod.POST).hasRole(Role.ADMINISTRATOR.toString());
                request.anyRequest().authenticated();
                //request.anyRequest().permitAll();
            })
            //.formLogin(Customizer.withDefaults()) //form based login for browser
            //.httpBasic(Customizer.withDefaults()) //Allow to log in postman
            //.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtFilter , UsernamePasswordAuthenticationFilter.class)
            .build();
    }

//    @Bean
//    public AuthenticationProvider authenticationProvider(){
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
//        provider.setUserDetailsService(userDetailsService);
//
//        return provider;
//    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
