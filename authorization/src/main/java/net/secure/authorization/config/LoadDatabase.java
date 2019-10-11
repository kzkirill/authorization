package net.secure.authorization.config;
import lombok.extern.slf4j.Slf4j;
import net.secure.authorization.entities.User;
import net.secure.authorization.repositories.UserRepository;

import static net.secure.authorization.utils.PasswordUtils.generateSecurePassword;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class LoadDatabase {

  @Bean
  CommandLineRunner initDatabase(UserRepository repository) {
    return args -> {
    	String securedPassword1 = generateSecurePassword("password1Secured", "keyWord1");
        log.info("Preloading " + repository.save(new User("login1Secured", securedPassword1, "keyWord1", "User 1")));
        log.info("Preloading " + repository.save(new User("login2", "password2", "keyWord2", "User 2")));
    };
  }
}