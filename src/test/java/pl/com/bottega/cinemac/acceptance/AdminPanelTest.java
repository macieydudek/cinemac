package pl.com.bottega.cinemac.acceptance;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.com.bottega.cinemac.application.AdminPanel;
import pl.com.bottega.cinemac.application.CinemaCatalog;
import pl.com.bottega.cinemac.application.CinemaDto;
import pl.com.bottega.cinemac.application.MovieShowingsDto;
import pl.com.bottega.cinemac.model.CinemaRepository;
import pl.com.bottega.cinemac.model.InvalidUserActionException;
import pl.com.bottega.cinemac.model.MovieRepository;
import pl.com.bottega.cinemac.model.showing.ShowingRepository;
import pl.com.bottega.cinemac.model.commands.CreateCinemaCommand;
import pl.com.bottega.cinemac.model.commands.CreateMovieCommand;
import pl.com.bottega.cinemac.model.commands.CreateShowingsCommand;
import pl.com.bottega.cinemac.model.commands.ShowingCalendar;
import pl.com.bottega.cinemac.shared.DBCleaner;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AdminPanelTest {

    @Autowired
    AdminPanel adminPanel;

    @Autowired
    CinemaCatalog cinemaCatalog;

    @Autowired
    ShowingRepository showingRepository;

    @Autowired
    CinemaRepository cinemaRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    DBCleaner dbCleaner;

    @Before
    public void clean() {
        dbCleaner.clean();
    }


    @Test
    public void shouldCreateCinema() {
        //given
        CreateCinemaCommand cmd1 = new CreateCinemaCommand();
        cmd1.setName("Kosmos");
        cmd1.setCity("Lublin");

        CreateCinemaCommand cmd2 = new CreateCinemaCommand();
        cmd2.setName("Bajka");
        cmd2.setCity("Lublin");

        CreateCinemaCommand cmd3 = new CreateCinemaCommand();
        cmd3.setName("Kosmos");
        cmd3.setCity("Łódź");
        //when
        adminPanel.createCinema(cmd1);
        adminPanel.createCinema(cmd2);
        adminPanel.createCinema(cmd3);
        //then
            List<CinemaDto> cinemas = cinemaCatalog.getCinemas();
        assertThat(cinemas.size()).isEqualTo(3);
    }

    @Test(expected = InvalidUserActionException.class)
    public void shouldNotAllowToAddSameCinemaTwice() {
        //given
        CreateCinemaCommand cmd1 = new CreateCinemaCommand();
        cmd1.setName("Kosmos");
        cmd1.setCity("Lublin");
        //when
        adminPanel.createCinema(cmd1);
        adminPanel.createCinema(cmd1);
    }

    @Test
    public void shouldCreateShowingsOfDates() {
        CreateCinemaCommand cmd1 = new CreateCinemaCommand();
        cmd1.setName("Kosmos");
        cmd1.setCity("Lublin");
        adminPanel.createCinema(cmd1);
        CreateMovieCommand cmd2 = new CreateMovieCommand();
        cmd2.setTitle("Test");
        cmd2.setDescription("testowy");
        cmd2.setLength(123);
        cmd2.setMinAge(12);
        cmd2.setActors(new HashSet<>(Arrays.asList("Brad Test")));
        cmd2.setGenres(new HashSet<>(Arrays.asList("Melotest")));
        adminPanel.createMovie(cmd2);

        CreateShowingsCommand cmd3 = new CreateShowingsCommand();
        cmd3.setCinemaId(cinemaCatalog.getCinemas().get(0).getId());
        cmd3.setMovieId(cinemaCatalog.getMovies().get(0).getId());
        String firstShowing = LocalDateTime.now().plusDays(1L).format(DateTimeFormatter.ofPattern("yyyy/MM/dd kk:mm"));
        String secondShowing = LocalDateTime.now().plusDays(1L).plusMinutes(1L).format(DateTimeFormatter.ofPattern("yyyy/MM/dd kk:mm"));
        cmd3.setDates(Arrays.asList(firstShowing, secondShowing));
        adminPanel.createShowings(cmd3);

        List<MovieShowingsDto> showings = cinemaCatalog.getShowings(cmd3.getCinemaId(), LocalDateTime.parse(firstShowing, DateTimeFormatter.ofPattern("yyyy/MM/dd kk:mm")).toLocalDate());
        assertThat(showings.size()).isEqualTo(1);
        assertThat(showings.get(0).getMovie().getId()).isEqualTo(cmd3.getMovieId());
    }

    @Test
    public void shouldCreateShowingsOfCalendar() {
        CreateCinemaCommand cmd1 = new CreateCinemaCommand();
        cmd1.setName("Kosmos");
        cmd1.setCity("Lublin");
        adminPanel.createCinema(cmd1);
        CreateMovieCommand cmd2 = new CreateMovieCommand();
        cmd2.setTitle("Test");
        cmd2.setDescription("testowy");
        cmd2.setLength(123);
        cmd2.setMinAge(12);
        cmd2.setActors(new HashSet<>(Arrays.asList("Brad Test")));
        cmd2.setGenres(new HashSet<>(Arrays.asList("Melotest")));
        adminPanel.createMovie(cmd2);

        CreateShowingsCommand cmd3 = new CreateShowingsCommand();
        cmd3.setCinemaId(cinemaCatalog.getCinemas().get(0).getId());
        cmd3.setMovieId(cinemaCatalog.getMovies().get(0).getId());
        String fromDate = LocalDateTime.now().plusDays(1L).format(DateTimeFormatter.ofPattern("yyyy/MM/dd kk:mm"));
        String untilDate = LocalDateTime.now().plusDays(1L).plusMinutes(20L).format(DateTimeFormatter.ofPattern("yyyy/MM/dd kk:mm"));
        ShowingCalendar calendar = new ShowingCalendar();
        calendar.setFromDate(fromDate);
        calendar.setUntilDate(untilDate);
        calendar.setWeekDays(Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"));
        calendar.setHours(Arrays.asList(LocalTime.now().plusMinutes(1L).format(DateTimeFormatter.ofPattern("kk:mm"))));
        cmd3.setCalendar(calendar);
        adminPanel.createShowings(cmd3);

        List<MovieShowingsDto> showings = cinemaCatalog.getShowings(cmd3.getCinemaId(), LocalDateTime.parse(fromDate, DateTimeFormatter.ofPattern("yyyy/MM/dd kk:mm")).toLocalDate());
        assertThat(showings.size()).isEqualTo(1);
    }
}

