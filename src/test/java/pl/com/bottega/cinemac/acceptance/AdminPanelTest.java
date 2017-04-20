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
import pl.com.bottega.cinemac.model.InvalidUserActionException;
import pl.com.bottega.cinemac.model.ShowingRepository;
import pl.com.bottega.cinemac.model.commands.CreateCinemaCommand;
import pl.com.bottega.cinemac.model.commands.CreateMovieCommand;
import pl.com.bottega.cinemac.model.commands.CreateShowingsCommand;

import java.util.Arrays;
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
    public void shouldCreateShowingsUsingDates() {
        CreateCinemaCommand cmd1 = new CreateCinemaCommand();
        cmd1.setName("Kosmos");
        cmd1.setCity("Lublin");
        adminPanel.createCinema(cmd1);

        CreateMovieCommand cmd2 = new CreateMovieCommand();
        adminPanel.createMovie(cmd2);

        CreateShowingsCommand cmd = new CreateShowingsCommand();
        cmd.setCinemaId(1L);
        cmd.setMovieId(1L);
        cmd.setDates(Arrays.asList(new String[]{"2017/10/12 15:00", "2017/10/12 18:00", "2017/10/12 21:00"}));

        adminPanel.createShowings(cmd);
    }
}

