package pl.com.bottega.cinemac.infrastructure;

import org.springframework.context.annotation.Bean;
import pl.com.bottega.cinemac.application.AdminPanel;
import pl.com.bottega.cinemac.application.CinemaCatalog;
import pl.com.bottega.cinemac.application.ReservationProcess;
import pl.com.bottega.cinemac.application.implementation.StandardAdminPanel;
import pl.com.bottega.cinemac.application.implementation.StandardReservationProcess;
import pl.com.bottega.cinemac.model.*;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public AdminPanel adminPanel(CinemaRepository cinemaRepository, ShowingsFactory showingsFactory,
                                 MovieRepository movieRepository, ShowingRepository showingRepository) {
        return new StandardAdminPanel(cinemaRepository, showingsFactory, movieRepository, showingRepository);
    }

    @Bean
    public CinemaCatalog cinemaCatalog() {
        return new JPACinemaCatalog();
    }

    @Bean
    public CinemaRepository cinemaRepository() {
        return new JPACinemaRepository();
    }

    @Bean
    public MovieRepository movieRepository() {
        return new JPAMovieRepository();
    }

    @Bean
    public ShowingRepository showingRepository() {
        return new JPAShowingRepository();
    }

    @Bean
    public ShowingsFactory showingsFactory() {
        return new ShowingsFactory();
    }

    @Bean
    public ReservationProcess reservationProcess(PriceCalculator priceCalculator, ShowingRepository showingRepository) {
        return new StandardReservationProcess(priceCalculator, showingRepository);
    }

    @Bean
    public PriceCalculator priceCalculator(ShowingRepository showingRepository) { return new PriceCalculator(showingRepository);}
}
