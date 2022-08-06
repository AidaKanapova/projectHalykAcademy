package kz.halykacademy.bookstore.config;

import kz.halykacademy.bookstore.repository.UserRepository;
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
public class CustomSecurityConfiguration  {

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository){
        return new  UserDetailsImpl(userRepository);
    }

   @Bean
    public SecurityFilterChain customFilterChain(HttpSecurity http) throws Exception {
       return http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/**").authenticated()
               .antMatchers(HttpMethod.POST,"/orders/**").authenticated()
               .antMatchers(HttpMethod.PUT,"/orders/**").authenticated()


/*
               .antMatchers(HttpMethod.PUT,"/orders/updateOrder/{userId}").access("@userSecurity.hasUserId(authentication,#userId)")
*/


                .antMatchers( "/**").hasAuthority("ADMIN")
                .and().httpBasic(Customizer.withDefaults()).build();
    }



}
