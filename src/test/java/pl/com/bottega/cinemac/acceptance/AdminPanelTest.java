package pl.com.bottega.cinemac.acceptance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemac.application.AdminPanel;
import pl.com.bottega.cinemac.application.CinemaCatalog;
import pl.com.bottega.cinemac.application.CinemaDto;
import pl.com.bottega.cinemac.model.CinemaRepository;
import pl.com.bottega.cinemac.model.InvalidUserActionException;
import pl.com.bottega.cinemac.model.MovieRepository;
import pl.com.bottega.cinemac.model.ShowingRepository;
import pl.com.bottega.cinemac.model.commands.CreateCinemaCommand;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
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

    /*@Test
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
        cmd3.setCinemaId(1L);
        cmd3.setMovieId(1L);
        cmd3.setDates(Arrays.asList("2017/04/21 15:00", "2017/04/22 15:00", "2017/04/23 20:00"));
        adminPanel.createShowings(cmd3);

        assertThat(showingRepository.isAlreadyAdded(new Showing(LocalDateTime.of(2017, 4, 21, 15, 0), cinemaRepository.get(1L), movieRepository.get(1L)))).isTrue();
    }*/
}

