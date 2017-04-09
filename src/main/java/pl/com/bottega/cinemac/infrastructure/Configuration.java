package pl.com.bottega.cinemac.infrastructure;

import org.springframework.context.annotation.Bean;
import pl.com.bottega.cinemac.application.AdminPanel;
import pl.com.bottega.cinemac.application.CinemaCatalog;
import pl.com.bottega.cinemac.application.implementation.StandardAdminPanel;
import pl.com.bottega.cinemac.model.CinemaRepository;

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
