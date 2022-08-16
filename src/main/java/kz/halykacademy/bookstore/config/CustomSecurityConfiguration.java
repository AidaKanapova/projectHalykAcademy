package kz.halykacademy.bookstore.config;

import kz.halykacademy.bookstore.errors.MyBasicAuthenticationEntryPoint;
import kz.halykacademy.bookstore.repository.UserRepository;
import kz.halykacademy.bookstore.service.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class CustomSecurityConfiguration {

    @Autowired
    private MyBasicAuthenticationEntryPoint myBasicAuthenticationEntryPoint;

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new UserDetailsImpl(userRepository);
    }

    @Bean
    public SecurityFilterChain customFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/v1/**").authenticated()
                .antMatchers(HttpMethod.POST, "/api/v1/orders/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/v1/orders/**").authenticated()
                .antMatchers(HttpMethod.POST,"api/v1/orders/updateOrderByAdmin").hasAuthority("ADMIN")
                .antMatchers( "/api/v1/**").hasAuthority("ADMIN")
                .and().formLogin()
                .and().httpBasic().authenticationEntryPoint(myBasicAuthenticationEntryPoint);
        return http.build();

    }


}
