package net.secure.authorization.config;
import lombok.extern.slf4j.Slf4j;
import net.secure.authorization.entities.User;
import net.secure.authorization.repositories.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class LoadDatabase {

  @Bean
  CommandLineRunner initDatabase(UserRepository repository) {
    return args -> {
        log.info("Preloading " + repository.save(new User("login1", "password1", "keyWord1", "User 1")));
        log.info("Preloading " + repository.save(new User("login2", "password2", "keyWord2", "User 2")));
    };
  }
}