package br.com.curso.api.config;

import br.com.curso.api.domain.Users;
import br.com.curso.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository repository;

    @Bean
    public void  startDB() {
        Users user1 = new Users(null, "Saulo", "saulobraga_es@hotmail.com", "123");
        Users user2 = new Users(null, "Braga", "braga_es@hotmail.com", "123");

        repository.saveAll(List.of(user1,user2));
    }
}
