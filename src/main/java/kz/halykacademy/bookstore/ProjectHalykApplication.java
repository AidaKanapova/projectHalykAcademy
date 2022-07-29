package kz.halykacademy.bookstore;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

public class ProjectHalykApplication {
    @Bean
    public ModelMapper modelMapper(){
        return  new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(ProjectHalykApplication.class, args);
    }

}
