package be.ucll.dentravak;

import be.ucll.dentravak.repository.SandwichRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public SandwichRepository sandwichRepository() {
        return new SandwichRepository();
    }
}
