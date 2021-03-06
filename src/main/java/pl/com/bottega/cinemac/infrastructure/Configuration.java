package pl.com.bottega.cinemac.infrastructure;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import pl.com.bottega.cinemac.application.*;
import pl.com.bottega.cinemac.application.implementation.*;
import pl.com.bottega.cinemac.model.*;
import pl.com.bottega.cinemac.model.payment.PaymentFacade;
import pl.com.bottega.cinemac.model.payment.StripePaymentFacade;
import pl.com.bottega.cinemac.model.pricing.PriceCalculator;
import pl.com.bottega.cinemac.model.reservation.ReservationRepository;
import pl.com.bottega.cinemac.model.showing.ShowingRepository;
import pl.com.bottega.cinemac.model.showing.ShowingsFactory;

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
    public ReservationProcess reservationProcess(PriceCalculator priceCalculator, ShowingRepository showingRepository, ReservationRepository reservationRepository) {
        return new StandardReservationProcess(priceCalculator, showingRepository, reservationRepository);
    }

    @Bean
    public PaymentCollector paymentCollector(ReservationRepository reservationRepository, PaymentFacade paymentFacade,
                                             ApplicationEventPublisher applicationEventPublisher, ShowingRepository showingRepository,
                                             PriceCalculator priceCalculator) {
        return new StandardPaymentCollector(reservationRepository, paymentFacade, applicationEventPublisher, showingRepository, priceCalculator);
    }

    @Bean
    public PaymentFacade paymentFacade() {
        return new StripePaymentFacade();
    }

    @Bean
    public ReservationRepository reservationRepository(){
        return new JPAReservationRepository();
    }

    @Bean
    public PriceCalculator priceCalculator(ShowingRepository showingRepository) { return new PriceCalculator(showingRepository);}

    @Bean
    public TicketPrinter ticketPrinter(ReservationRepository reservationRepository, ShowingRepository showingRepository) {
        return new ITextTicketPrinter(reservationRepository, showingRepository);
    }

    @Bean
    public PaymentListener paymentListener(TicketMailer ticketMailer, ReservationRepository reservationRepository) {
        return new StandardPaymentListener(ticketMailer, reservationRepository);
    }

    @Bean
    public TicketMailer ticketMailer(TicketPrinter ticketPrinter, JavaMailSender javaMailSender) {
        return new SMTPGmailTicketMailer(ticketPrinter, javaMailSender);
    }

    @Bean
    public ReservationCatalog reservationCatalog(PriceCalculator priceCalculator){
        return new JPAReservationCatalog(priceCalculator);
    }

}

