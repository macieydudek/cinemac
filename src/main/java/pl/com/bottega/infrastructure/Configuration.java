package pl.com.bottega.infrastructure;

import org.springframework.context.annotation.Bean;
import pl.com.bottega.application.AdminPanel;
import pl.com.bottega.application.CinemaCatalog;
import pl.com.bottega.application.implementation.StandardAdminPanel;
import pl.com.bottega.model.CinemaRepository;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public AdminPanel adminPanel(CinemaRepository cinemaRepository) {
        return new StandardAdminPanel(cinemaRepository);
    }

    @Bean
    public CinemaCatalog cinemaCatalog() {
        return new JPACinemaCatalog();
    }

    @Bean
    public CinemaRepository cinemaRepository() {
        return new JPACinemaRepository();
    }
}
