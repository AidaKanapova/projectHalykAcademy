package kz.halykacademy.bookstore.config;

import kz.halykacademy.bookstore.errors.MyBasicAuthenticationEntryPoint;
import kz.halykacademy.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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
                .antMatchers(HttpMethod.GET, "/**").authenticated()
                .antMatchers(HttpMethod.POST, "/orders/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/orders/**").authenticated()
                .antMatchers( "/**").hasAuthority("ADMIN")
                .and().formLogin()
                .and().httpBasic().authenticationEntryPoint(myBasicAuthenticationEntryPoint);
        return http.build();

    }


}
